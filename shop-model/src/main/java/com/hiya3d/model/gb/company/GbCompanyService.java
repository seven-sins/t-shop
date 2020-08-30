package com.hiya3d.model.gb.company;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.hiya3d.base.validator.IntValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.LogicDelete;

/**
 * 客服
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:11:46
 */
@Data
public class GbCompanyService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(value = "id")
	@Length(max = 32, message = "id输入超出最大长度(32)")
	private String id;

	@ApiModelProperty(value = "客服号")
	@Length(max = 50, message = "客服号输入超出最大长度(50)")
	@NotBlank(message = "客服号不能为空")
	private String service;

	@ApiModelProperty(value = "客服二维码")
	@Length(max = 500, message = "客服二维码输入超出最大长度(500)")
	private String serviceQrcode;

	@ApiModelProperty(value = "是否默认客服(1:是,0:否)")
	@IntValue(message = "是否默认客服(1:是,0:否)取值超出范围")
	@NotNull(message = "是否默认客服(1:是,0:否)不能为空")
	private Integer isNormal;

	@LogicDelete
	@ApiModelProperty(value = "是否删除(1:是,0:否)")
	@IntValue(message = "是否删除(1:是,0:否)取值超出范围")
	private Integer isDeleted;

	@ApiModelProperty(value = "是否停用(1:是,0:否)")
	@IntValue(message = "是否停用(1:是,0:否)取值超出范围")
	private Integer isDisabled;

	@ApiModelProperty(value = "公司ID")
	@Length(max = 32, message = "公司ID输入超出最大长度(32)")
	@NotBlank(message = "公司ID不能为空")
	private String companyId;

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