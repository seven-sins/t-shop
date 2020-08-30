package com.hiya3d.common.excel.style;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import com.hiya3d.common.excel.domain.Format;

/**
 * 数据样式创建器
 * 
 * @author Rex.Tan
 * @date 2019年10月15日 下午2:13:42
 */
public abstract class DataCellstyleCreater implements IDataCellstyleCreater {

	private Map<Format, CellStyle> cellStylesCache = new HashMap<>();

	public CellStyle createStyle(Workbook workbook, Format format) {
		CellStyle style = cellStylesCache.get(format);
		if (style != null) {
			return style;
		}
		if (format == Format.DATE) {
			style = createDateStyle(workbook);
		} else if (format == Format.ACCOUNTANT) {
			style = createAccountantStyle(workbook);
		} else if (format == Format.GENERAL) {
			style = createGeneralStyle(workbook);
		} else if (format == Format.PERCENT) {
			style = createPercentStyle(workbook);
		} else if (format == Format.INTEGER) {
			style = createIntegerStyle(workbook);
		} else {
			style = createTextStyle(workbook);
		}

		cellStylesCache.put(format, style);

		return style;
	}

	public abstract CellStyle createTextStyle(Workbook workbook);

	public abstract CellStyle createIntegerStyle(Workbook workbook);

	public abstract CellStyle createGeneralStyle(Workbook workbook);

	public abstract CellStyle createDateStyle(Workbook workbook);

	public abstract CellStyle createAccountantStyle(Workbook workbook);

	public abstract CellStyle createPercentStyle(Workbook workbook);
}
