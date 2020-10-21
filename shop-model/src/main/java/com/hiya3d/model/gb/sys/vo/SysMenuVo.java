package com.hiya3d.model.gb.sys.vo;

import java.util.List;

import com.hiya3d.model.gb.sys.SysMenu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysMenuVo extends SysMenu {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "子菜单")
	private List<SysMenuVo> children;
	
	@ApiModelProperty(value = "菜单名称(修复前端控件显示问题)")
	private String text;
	
	@ApiModelProperty(value = "图标样式")
	private String iconCls;
	
	@ApiModelProperty(value = "当前节点是否选中状态(修复前端控件显示问题)")
	private Boolean isSelect = false;
}
