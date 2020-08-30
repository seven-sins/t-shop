package com.hiya3d.common.excel;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.ss.usermodel.Workbook;

import com.hiya3d.common.excel.domain.ExcelConfig;

/**
 * 导出处理器
 * @author Rex.Tan
 * @date 2019年10月15日 下午2:14:17
 */
public class ExcelExportHandler<T> implements ResultHandler<T> {
	
	private ExcelUtil<T> excelExport;
	public ExcelExportHandler(ExcelConfig config,Workbook workbook) {
		this.excelExport = new ExcelUtil<T>(workbook, config);
	}

	/**
	 *行数据处理：填充行数据
	 */
	@Override
	public void handleResult(ResultContext<? extends T> context) {
		excelExport.createDataRow(context.getResultObject());
	}
}
