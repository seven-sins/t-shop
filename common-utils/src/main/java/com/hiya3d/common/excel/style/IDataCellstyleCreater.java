package com.hiya3d.common.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import com.hiya3d.common.excel.domain.Format;

/**
 * 数据单元格样式创建器
 * @author Rex.Tan
 * @date 2019年10月15日 下午2:13:09
 */
public interface IDataCellstyleCreater {
	
	/**
	 * 创建单元格样式
	 * @param workbook excel文档
	 * @param format 单元格格式
	 * @return
	 */
	public CellStyle createStyle(Workbook workbook, Format format);
}
