package com.hiya3d.common.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.hiya3d.base.exception.CustomException;
import com.hiya3d.common.utils.DateUtils;
import com.hiya3d.common.utils.LogUtil;

/**
 * @author rex.tan
 * @date 2019年11月29日 下午2:25:21
 */
public class ReadExcel {
	private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";
    
    /**
	 * @author Rex.Tan
	 * @date 2019年11月29日 下午2:10:11
	 * @param file
	 * @return
	 */
	public static List<Map<String, String>> read(MultipartFile file, String sheetName) {
		// 声明一个新的工作表
		Sheet sheet;
		try {
			sheet = getSheetBySheetName(file,sheetName);
			if(sheet == null) {
				throw new CustomException("导入模板错误，请重新下载模板");
			}
		} catch (Exception e) {
			LogUtil.printException("读取excel出错, fileName: " + file.getName(), e);
			if(e instanceof CustomException) {
				throw e;
			}
			throw new CustomException("读取excel文件出错");
		}
		return toList(sheet);
	}

	private static Sheet getSheetBySheetName(MultipartFile file, String sheetName) {
		Sheet sheet = null;
		try  {
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			Workbook workbook = null;
	        if (XLS.equalsIgnoreCase(fileType)) {
	            workbook = new HSSFWorkbook(file.getInputStream());
	        } else if (XLSX.equalsIgnoreCase(fileType)) {
	            workbook = new XSSFWorkbook(file.getInputStream());
	        }
	        sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			LogUtil.printException("读取excel出错", e);
			throw new CustomException("文件读取异常, " + e.getMessage());
		}

		return sheet;
	}
	
	/**
	 * @author Rex.Tan
	 * @date 2019年11月29日 下午2:10:11
	 * @param file
	 * @return
	 */
	public static List<Map<String, String>> read(MultipartFile file) {
		// 声明一个新的工作表
		Sheet sheet;
		try {
			sheet = getSheet(file);
		} catch (Exception e) {
			LogUtil.printException("读取excel出错", e);
			throw new CustomException("读取excel文件出错");
		}

		return toList(sheet);
	}

	private static Sheet getSheet(MultipartFile file) {
		Sheet sheet = null;
		try  {
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			Workbook workbook = null;
	        if (XLS.equalsIgnoreCase(fileType)) {
	            workbook = new HSSFWorkbook(file.getInputStream());
	        } else if (XLSX.equalsIgnoreCase(fileType)) {
	            workbook = new XSSFWorkbook(file.getInputStream());
	        }
			sheet = workbook.getSheetAt(0);
		} catch (Exception e) {
			LogUtil.printException("读取excel出错", e);
			throw new CustomException("文件读取异常, " + e.getMessage());
		}

		return sheet;
	}

	/**
	 * 读取Excel
	 * 
	 * @author Rex.Tan
	 * @date 2019年3月29日 下午2:21:58
	 * @param sheet
	 * @return
	 */
	private static List<Map<String, String>> toList(Sheet sheet) {
		// 得到Excel的行数
		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalCells = 0;
		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		// 第一行的数据
		ArrayList<String> keys = new ArrayList<>();
		// 获取第一行的数据
		Row row1 = sheet.getRow(0);
		for (int i = 0; i < totalCells; i++) {
			Cell cell = row1.getCell(i);
			String value = cell.getStringCellValue();
			keys.add(value);
		}
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = new LinkedHashMap<>();
		// 循环Excel行数,从第二行开始。标题不入库
		for (int i = 1; i < totalRows; i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			map = new LinkedHashMap<>();
			// 循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				// 将列的信息添加到map
				map.put(keys.get(c), getStringValueFromCell(cell));
			}

			Integer totalCell = 0;
			Integer emptyCell = 0;
			Iterator<Entry<String, String>> entries = map.entrySet().iterator();
			while (entries.hasNext()) {
				totalCell++;
				Entry<String, String> entry = entries.next();
				if (entry.getValue() == null || "".equals(entry.getValue())) {
					emptyCell++;
				}
			}
			if (totalCell > 0 && !emptyCell.equals(totalCell)) {
				list.add(map);
			}
			if (emptyCell.equals(totalCell)) {
				break;
			}
		}

		return list;
	}

	/**
	 * 读入excel的内容转换成字符串
	 * 
	 * @author Rex.Tan
	 * @date 2019年11月29日 下午2:24:04
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getStringValueFromCell(Cell cell) {
		String value = "";
		if (cell == null) {
			return value;
		}
		// 判断数据类型
		switch (cell.getCellType()) {
		case FORMULA:
			value = "" + cell.getCellFormula();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				return DateUtils.date2StringNormal(date, null);
			} else {
				cell.setCellType(CellType.STRING);
				value = "" + cell.getStringCellValue();
				if (value.endsWith(".0")) {
					value = value.substring(0, value.indexOf("."));
				}
			}
			break;
		case STRING:
			value = cell.getStringCellValue();
			break;
		default:
			break;
		}
		if(value != null) {
			value = value.trim();
		}

		return value;
	}

}
