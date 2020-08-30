package com.hiya3d.common.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 单元格样式创建器
 * @author Rex.Tan
 * @date 2019年10月15日 下午2:13:16
 */
public interface ICellstyleCreater {
	
	/**
	 * 创建单元格样式
	 * @param workbook excel文档
	 * @return
	 */
	public CellStyle createStyle(Workbook workbook);
}
