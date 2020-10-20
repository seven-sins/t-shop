package com.hiya3d.model.gb.goods.vo;

import java.util.List;

import com.hiya3d.model.gb.goods.GbCategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GbCategoryVo extends GbCategory {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "子分类")
	private List<GbCategoryVo> children;
	
	@ApiModelProperty(value = "分类名称(修复前端控件显示问题)")
	private String text;
	
	@ApiModelProperty(value = "主题名称")
	private String themeName;
}
