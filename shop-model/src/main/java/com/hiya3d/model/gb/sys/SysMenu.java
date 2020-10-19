package com.hiya3d.model.gb.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.hiya3d.base.validator.IntValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.LogicDelete;
import tk.mybatis.mapper.annotation.Order;

/**
 * 菜单
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:12:20
 */
@Data
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(value = "id")
	@Length(max = 32, message = "id输入超出最大长度(32)")
	private String id;

	@ApiModelProperty(value = "菜单名称")
	@Length(max = 50, message = "菜单名称输入超出最大长度(50)")
	@NotBlank(message = "菜单名称不能为空")
	private String menuName;

	@ApiModelProperty(value = "菜单url")
	@Length(max = 100, message = "菜单url输入超出最大长度(100)")
	private String url;

	@ApiModelProperty(value = "图标")
	@Length(max = 50, message = "图标输入超出最大长度(50)")
	private String icon;

	@ApiModelProperty(value = "是否菜单(1:是,0:否)")
	@IntValue(message = "是否菜单(1:是,0:否)取值超出范围")
	private Integer isMenu;

	@ApiModelProperty(value = "是否功能按钮(1:是,0:否)")
	@IntValue(message = "是否功能按钮(1:是,0:否)取值超出范围")
	private Integer isBtn;

	@ApiModelProperty(value = "权限编码(is_btn==1时必填)")
	@Length(max = 50, message = "权限编码(is_btn==1时必填)输入超出最大长度(50)")
	private String authCode;

	@ApiModelProperty(value = "上级菜单ID")
	@Length(max = 32, message = "上级菜单ID输入超出最大长度(32)")
	private String parentId;
	
	@ApiModelProperty(value = "排序")
	@Order("ASC")
	private Integer sort;

	@LogicDelete
	@ApiModelProperty(value = "是否删除(1:是,0:否)")
	@IntValue(message = "是否删除(1:是,0:否)取值超出范围")
	private Integer isDeleted;

	@ApiModelProperty(value = "是否停用(1:是,0:否)")
	@IntValue(message = "是否停用(1:是,0:否)取值超出范围")
	private Integer isDisabled;

	@ApiModelProperty(value = "创建人")
	@Length(max = 50, message = "创建人输入超出最大长度(50)")
	private String createdBy;

	@ApiModelProperty(value = "创建人ID")
	@Length(max = 32, message = "创建人ID输入超出最大长度(32)")
	private String createdUserId;

	@ApiModelProperty(value = "创建时间")
	private Date createdTime;

	@ApiModelProperty(value = "更新人")
	@Length(max = 50, message = "更新人输入超出最大长度(50)")
	private String updatedBy;

	@ApiModelProperty(value = "更新人ID")
	@Length(max = 32, message = "更新人ID输入超出最大长度(32)")
	private String updatedUserId;

	@ApiModelProperty(value = "更新时间")
	private Date updatedTime;

}