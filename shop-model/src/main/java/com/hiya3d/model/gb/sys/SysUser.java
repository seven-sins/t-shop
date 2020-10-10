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

/**
 * 用户
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:18:09
 */
@Data
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(value = "id")
	@Length(max = 32, message = "id输入超出最大长度(32)")
	private String id;

	@ApiModelProperty(value = "用户名")
	@Length(max = 50, message = "用户名输入超出最大长度(50)")
	@NotBlank(message = "用户名不能为空")
	private String userName;

	@ApiModelProperty(value = "用户编码")
	@Length(max = 50, message = "用户编码输入超出最大长度(50)")
	@NotBlank(message = "用户编码不能为空")
	private String userCode;

	@ApiModelProperty(value = "登录账号")
	@Length(max = 50, message = "登录账号输入超出最大长度(50)")
	@NotBlank(message = "登录账号不能为空")
	private String loginAccount;

	@ApiModelProperty(value = "手机号")
	@Length(max = 30, message = "手机号输入超出最大长度(30)")
	private String phone;

	@ApiModelProperty(value = "身份证")
	@Length(max = 30, message = "身份证输入超出最大长度(30)")
	private String idCard;

	@ApiModelProperty(value = "生日")
	private Date birthday;

	@ApiModelProperty(value = "性别(1:男,0:女)")
	@IntValue(message = "性别(1:男,0:女)取值超出范围")
	private Integer sex;

	@ApiModelProperty(value = "年龄")
	@IntValue(message = "年龄取值超出范围")
	private Integer age;

	@ApiModelProperty(value = "微信openid")
	@Length(max = 50, message = "微信openid输入超出最大长度(50)")
	private String openid;

	@ApiModelProperty(value = "微信unionid")
	@Length(max = 50, message = "微信unionid输入超出最大长度(50)")
	private String unionid;

	@ApiModelProperty(value = "头像")
	@Length(max = 500, message = "头像输入超出最大长度(500)")
	private String headImg;

	@ApiModelProperty(value = "是否系统用户(可以在后台操作)")
	@IntValue(message = "是否系统用户(可以在后台操作)取值超出范围")
	private Integer isSysUser;

	@LogicDelete
	@ApiModelProperty(value = "是否删除(1:是,0:否)")
	@IntValue(message = "是否删除(1:是,0:否)取值超出范围")
	private Integer isDeleted;

	@ApiModelProperty(value = "是否停用(1:是,0:否)")
	@IntValue(message = "是否停用(1:是,0:否)取值超出范围")
	private Integer isDisabled;

	@ApiModelProperty(value = "店铺ID")
	@Length(max = 32, message = "店铺ID输入超出最大长度(32)")
	@NotBlank(message = "店铺ID不能为空")
	private String shopId;

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