package com.hiya3d.model.sp.voucher;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * 优惠券
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:44:34
 */
@Data
public class SpVoucher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ApiModelProperty(value = "id")
	@Length(max = 32, message = "id输入超出最大长度(32)")
	private String id;

	@ApiModelProperty(value = "优惠金额")
	private BigDecimal amount;

	@ApiModelProperty(value = "类型(1:满减,2:抵扣)")
	@IntValue(message = "类型(1:满减,2:抵扣)取值超出范围")
	@NotNull(message = "类型(1:满减,2:抵扣)不能为空")
	private Integer voucherType;

	@ApiModelProperty(value = "满减金额(类型==2时必填)")
	private BigDecimal fullAmount;

	@ApiModelProperty(value = "起始有效时间")
	private Date startTime;

	@ApiModelProperty(value = "截止有效时间")
	private Date endTime;

	@ApiModelProperty(value = "备注")
	@Length(max = 500, message = "备注输入超出最大长度(500)")
	private String remark;

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