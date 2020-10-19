package com.hiya3d.model.gb.sys.vo;

import com.hiya3d.model.gb.sys.SysUser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserVo extends SysUser {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "店铺名称")
	private String shopName;
}
