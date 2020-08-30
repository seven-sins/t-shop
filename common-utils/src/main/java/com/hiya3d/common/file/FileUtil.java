package com.hiya3d.common.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hiya3d.base.exception.CustomException;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.common.excel.ExcelUtil;
import com.hiya3d.common.excel.domain.ColumnConfig;
import com.hiya3d.common.excel.domain.ExcelConfig;
import com.hiya3d.common.utils.Assert;
import com.hiya3d.common.utils.DateUtils;

/**
 * 文件操作工具类
 * @author Rex.Tan
 * @date 2019年10月15日 下午6:08:27
 */
@Component
@AutoConfigureAfter({ SysConfig.class })
public class FileUtil {
	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
	/**
	 * 图片目录名称
	 */
	private static final String IMAGES_PATH = "images";
	@Autowired
	SysConfig sysConfig;
	
	/**
	 * @author Rex.Tan
	 * @date 2020年6月2日 下午3:27:20
	 * @param columnConfigs
	 * @param list
	 * @param title
	 * @param excelTitle
	 * @return
	 */
	@SuppressWarnings("all") 
	public Map<String, String> writeExcel(ColumnConfig[] columnConfigs, List list, String title, boolean showExcelTitle){
		Workbook wb = new HSSFWorkbook();
        ExcelConfig excelConfig = new ExcelConfig(title, columnConfigs);
        if(showExcelTitle) {
        	excelConfig.setTitle(title);
        }
        ExcelUtil<Object> export = new ExcelUtil<>(wb, excelConfig);
        export.createDataRows(list);
        /**
         * 3.将文件写入服务器目录
         */
        String fileName = writeExcel(wb, title);
        /**
         * 4.返回文件下载地址
         */
        Map<String, String> map = new HashMap<>();
        map.put("url", generatorDownloadUrl(fileName));
        
        return map;
	}
	
	/**
	 * @author Rex.Tan
	 * @date 2020年6月2日 下午3:27:20
	 * @param columnConfigs
	 * @param list
	 * @param title
	 * @param excelTitle
	 * @return
	 */
	@SuppressWarnings("all") 
	public Map<String, String> writeExcel(ColumnConfig[] columnConfigs, List list, String title, boolean showExcelTitle,String sheetName){
		Workbook wb = new HSSFWorkbook();
        ExcelConfig excelConfig = new ExcelConfig(sheetName, columnConfigs);
        if(showExcelTitle) {
        	excelConfig.setTitle(title);
        }
        ExcelUtil<Object> export = new ExcelUtil<>(wb, excelConfig);
        export.createDataRows(list);
        /**
         * 3.将文件写入服务器目录
         */
        String fileName = writeExcel(wb, title);
        /**
         * 4.返回文件下载地址
         */
        Map<String, String> map = new HashMap<>();
        map.put("url", generatorDownloadUrl(fileName));
        
        return map;
	}
	
	@SuppressWarnings("all") 
	public Map<String, String> writeExcel(ColumnConfig[] columnConfigs, List list, String title){
		return writeExcel(columnConfigs, list, title, true);
	}
	
	/**
	 * 写入文件
	 * @param wb
	 * @return
	 * @author Rex.Tan
	 * @date 2019年10月15日 下午6:11:28
	 */
	public String writeExcel(Workbook wb, String name) {
		String filePath = getUserFolder();
		this.checkFolderExist(new File(filePath));
		// 将文件写入服务器目录
        String fileName = name + DateUtils.timestamp() + ".xls";
        String fullPath = filePath + "/" + fileName;
        try {
			FileOutputStream output = new FileOutputStream(fullPath);
			wb.write(output);
			output.flush();
			// 释放对象
			wb.close();
			output.close();
		} catch (FileNotFoundException e1) {
			LOG.error("=============创建文件失败: ", e1);
			throw new CustomException("创建文件失败, " + e1.getMessage());
		} catch (IOException e) {
			LOG.error("=============写入文件失败: ", e);
			throw new CustomException("写入文件失败, " + e.getMessage());
		}
        
        return fileName;
	}
	
	/**
	 * 下载文件物理路径
	 * @param fileName
	 * @return
	 * @author Rex.Tan
	 * @date 2019年10月16日 上午9:07:19
	 */
	public String getDownloadPhysicalPath(String fileName) {
		String filePath = getUserFolder();
		return filePath + "/" + fileName;
	}
	
	/**
	 * 下载文件
	 * @param response
	 * @param fileName
	 * @author Rex.Tan
	 * @date 2019年10月16日 上午9:46:24
	 */
	public void download(HttpServletResponse response, String fileName) {
		String fullPath = getDownloadPhysicalPath(fileName);
		File file = new File(fullPath);
		try {
			@SuppressWarnings("resource")
			FileInputStream inStream = new FileInputStream(file);
			// 设置响应头
			setResponseHeader(response, fileName);
			//
			byte[] buf = new byte[2048];
			int readLength;
			OutputStream output = response.getOutputStream();

			while (((readLength = inStream.read(buf)) != -1)) {
				output.write(buf, 0, readLength);
			}
		} catch (Exception e) {
			LOG.error("=============下载文件失败, " + fullPath, e);
			throw new CustomException("下载文件出错, " + e.getMessage());
		}
	}

	/**
	 * 生成文件下载路径
	 * @param fileName
	 * @return
	 * @author Rex.Tan
	 * @date 2019年10月16日 上午9:16:36
	 */
	public String generatorDownloadUrl(String fileName) {
		return sysConfig.getDomain() + "/download/" + fileName;
	}
	
	/**
	 * 每个用户一个存储目录
	 * @return
	 * @author Rex.Tan
	 * @date 2019年10月15日 下午6:15:30
	 */
	public String getUserFolder() {
		return sysConfig.getUploadPath(); // + "/" + UserContext.get().getUserId();
	}
	
	/**
	 * 图片保存路径
	 * @return
	 * @author Rex.Tan
	 * @date 2019年10月17日 上午9:34:34
	 */
	public String getUserImageFolder() {
		return sysConfig.getUploadImagePath() + "/" + IMAGES_PATH;
	}
	
	/**
	 * 获取图片访问地址
	 * @param fileName
	 * @return
	 * @author Rex.Tan
	 * @date 2019年10月17日 上午9:44:00
	 */
	public String generatorImageUrl(String fileName) {
		return sysConfig.getDomain() + "/ms_images/" + fileName;
	}
	
	/**
	 * 设置响应头
	 * @param response
	 * @param fileName
	 * @author Rex.Tan
	 * @date 2019年10月16日 上午9:34:36
	 */
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		// 清空输出流
		response.reset();
        response.setContentType("application/octet-stream;charset=UTF-8");
        try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "8859_1"));
		} catch (UnsupportedEncodingException e) {
			LOG.error("=============设置响应头失败: ", e);
			throw new CustomException("设置响应头失败, " + e.getMessage());
		}
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
    }
	
	/**
	 * 保存图片
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 * @author Rex.Tan
	 * @date 2019年10月17日 上午9:39:31
	 */
	public String saveImage(MultipartFile multipartFile) throws IOException {
		String path = getUserImageFolder();
		this.checkFolderExist(new File(path));
		
		FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
		String fileName = IdMaker.get() + ".jpg";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
		byte[] bs = new byte[1024];
		int len;
		while ((len = fileInputStream.read(bs)) != -1) {
			bos.write(bs, 0, len);
		}
		bos.flush();
		bos.close();
		
		return fileName;
	}
	
	/**
	 * 保存附件
	 * @param file
	 * @return
	 * @throws IOException
	 * @author Rex.Tan
	 * @date 2019年10月21日 下午3:43:09
	 */
	public String saveAdjunct(MultipartFile file) throws IOException {
		Assert.isTrue(!file.isEmpty(), "上传文件不能为空");
		// 对文文件的全名进行截取然后在后缀名进行删选。
		int begin = file.getOriginalFilename().indexOf(".");
		int last = file.getOriginalFilename().length();
		//获得文件后缀名
		String fileExtendName = file.getOriginalFilename().substring(begin, last);
		//
		String path = getUserFolder();
		this.checkFolderExist(new File(path));
		
		FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
		String fileName = IdMaker.get() + fileExtendName;
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
		byte[] bs = new byte[1024];
		int len;
		while ((len = fileInputStream.read(bs)) != -1) {
			bos.write(bs, 0, len);
		}
		bos.flush();
		bos.close();
		
		return fileName;
	}
	
	/**
	 * 目录不存在, 创建目录
	 * @param file
	 * @author Rex.Tan
	 * @date 2019年10月15日 下午6:12:23
	 */
	private void checkFolderExist(File file) {
		if (!file.exists()) {
			file.mkdirs();
        } 
	}
}
