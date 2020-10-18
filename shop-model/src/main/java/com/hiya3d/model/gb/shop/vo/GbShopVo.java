package com.hiya3d.model.gb.shop.vo;

import com.hiya3d.model.gb.shop.GbShop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GbShopVo extends GbShop {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主题名称")
	private String themeName;
}
