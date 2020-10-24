package com.hiya3d.model.sp.goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.hiya3d.base.validator.IntValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.LogicDelete;

/**
 * 商品
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:32:45
 */
@Data
@ApiModel("商品")
public class SpGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(value = "id")
	@Length(max = 32, message = "id输入超出最大长度(32)")
	private String id;

	@ApiModelProperty(value = "商品名称")
	@Length(max = 100, message = "商品名称输入超出最大长度(100)")
	@NotBlank(message = "商品名称不能为空")
	private String goodsName;

	@ApiModelProperty(value = "商品编码")
	@Length(max = 50, message = "商品编码输入超出最大长度(50)")
	@NotBlank(message = "商品编码不能为空")
	private String goodsCode;

	@ApiModelProperty(value = "分类ID")
	@Length(max = 32, message = "分类ID输入超出最大长度(32)")
	@NotBlank(message = "分类ID不能为空")
	private String categoryId;

	@ApiModelProperty(value = "单价(默认第一个sku单价)")
	private BigDecimal price;

	@ApiModelProperty(value = "原价")
	private BigDecimal oldPrice;

	@ApiModelProperty(value = "评论数(初始值)")
	@IntValue(message = "评论数(初始值)取值超出范围")
	@NotNull(message = "评论数(初始值)不能为空")
	private Integer commentQty;

	@ApiModelProperty(value = "销售数量(初始值)")
	@IntValue(message = "销售数量(初始值)取值超出范围")
	@NotNull(message = "销售数量(初始值)不能为空")
	private Integer saleQty;

	@ApiModelProperty(value = "商品类型(1:普通,2:秒杀,3:砍价,4:拼单)")
	@IntValue(message = "商品类型(1:普通,2:秒杀,3:砍价,4:拼单)取值超出范围")
	@NotNull(message = "商品类型(1:普通,2:秒杀,3:砍价,4:拼单)不能为空")
	private Integer goodsType;

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