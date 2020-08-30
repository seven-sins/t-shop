package com.hiya3d.model.sp.goods;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品详情
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:37:42
 */
@Data
public class SpGoodsDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "商品ID")
	@Length(max = 32, message = "商品ID输入超出最大长度(32)")
	@NotBlank(message = "商品ID不能为空")
	private String goodsId;

	@ApiModelProperty(value = "商品详情")
	private String goodsDetail;

}