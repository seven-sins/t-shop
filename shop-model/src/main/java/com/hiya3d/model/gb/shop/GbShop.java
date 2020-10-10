package com.hiya3d.model.gb.shop;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:08:09
 */
@Data
public class GbShop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ApiModelProperty(value = "id")
	@Length(max = 32, message = "id输入超出最大长度(32)")
	@Order("DESC")
	private String id;

	@ApiModelProperty(value = "公司编码")
	@Length(max = 50, message = "公司编码输入超出最大长度(50)")
	@NotBlank(message = "公司编码不能为空")
	private String companyCode;

	@ApiModelProperty(value = "公司名称")
	@Length(max = 100, message = "公司名称输入超出最大长度(100)")
	@NotBlank(message = "公司名称不能为空")
	private String companyName;

	@ApiModelProperty(value = "掌柜")
	@Length(max = 50, message = "掌柜输入超出最大长度(50)")
	@NotBlank(message = "掌柜不能为空")
	private String manager;

	@ApiModelProperty(value = "二维码")
	@Length(max = 500, message = "二维码输入超出最大长度(500)")
	@NotBlank(message = "二维码不能为空")
	private String qrcode;

	@ApiModelProperty(value = "客服")
	@Length(max = 50, message = "客服输入超出最大长度(50)")
	@NotBlank(message = "客服不能为空")
	private String service;

	@ApiModelProperty(value = "客服二维码")
	@Length(max = 500, message = "客服二维码输入超出最大长度(500)")
	private String serviceQrcode;

	@ApiModelProperty(value = "logo")
	@Length(max = 500, message = "logo输入超出最大长度(500)")
	private String logoImg;

	@ApiModelProperty(value = "公司地址")
	@Length(max = 100, message = "公司地址输入超出最大长度(100)")
	private String address;

	@ApiModelProperty(value = "联系电话")
	@Length(max = 50, message = "联系电话输入超出最大长度(50)")
	private String phone;

	@ApiModelProperty(value = "传真")
	@Length(max = 50, message = "传真输入超出最大长度(50)")
	private String fax;

	@ApiModelProperty(value = "城市ID")
	@Length(max = 32, message = "城市ID输入超出最大长度(32)")
	private String cityId;

	@ApiModelProperty(value = "城市")
	@Length(max = 50, message = "城市输入超出最大长度(50)")
	private String city;

	@ApiModelProperty(value = "省份ID")
	@Length(max = 32, message = "省份ID输入超出最大长度(32)")
	private String provinceId;

	@ApiModelProperty(value = "省份")
	@Length(max = 50, message = "省份输入超出最大长度(50)")
	private String province;

	@ApiModelProperty(value = "主题ID(经营主题)")
	@Length(max = 32, message = "主题ID(经营主题)输入超出最大长度(32)")
	@NotBlank(message = "主题ID(经营主题)不能为空")
	private String themeId;

	@ApiModelProperty(value = "总销量(订单数量)")
	@IntValue(message = "总销量(订单数量)取值超出范围")
	private Integer saleQty;

	@ApiModelProperty(value = "评分")
	private BigDecimal grade;

	@ApiModelProperty(value = "等级")
	private BigDecimal level;

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