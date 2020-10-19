package com.hiya3d.model.sp.category.vo;

import java.util.List;

import com.hiya3d.model.sp.category.SpGoodsCategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpGoodsCategoryVo extends SpGoodsCategory {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "子分类")
	private List<SpGoodsCategoryVo> children;
	
	@ApiModelProperty(value = "分类名称(修复前端控件显示问题)")
	private String text;
}
