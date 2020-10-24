package com.hiya3d.common.conf.mybatis.sql;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysSqlAnalysis implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "参数")
	private String parameter;
	
	@ApiModelProperty(value = "命名空间")
	private String mapper;
	
	@ApiModelProperty(value = "sql语句")
	private String sql;
	
	@ApiModelProperty(value = "性能分析")
	private String analysis;
}
