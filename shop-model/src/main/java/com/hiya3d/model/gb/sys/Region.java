package com.hiya3d.model.gb.sys;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.hiya3d.base.validator.IntValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全国省市数据库
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:12:20
 */
@Data
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(value = "id")
	@Length(max = 32, message = "id输入超出最大长度(32)")
	private String regionId;

	@ApiModelProperty(value = "城市编码")
	@Length(max = 50, message = "城市编码输入超出最大长度(50)")
	@NotBlank(message = "城市编码不能为空")
	private String regionCode;

	@ApiModelProperty(value = "城市名称")
	@Length(max = 50, message = "城市名称输入超出最大长度(50)")
	private String regionName;

	@ApiModelProperty(value = "上级城市ID")
	@Length(max = 32, message = "上级城市ID输入超出最大长度(32)")
	private String parentId;
	
	@ApiModelProperty(value = "城市级别")
	@IntValue
	private Integer regionLevel;
	
	@ApiModelProperty(value = "排序")
	@IntValue
	private Integer regionOrder;
	
	@ApiModelProperty(value = "英文名称")
	private String regionNameEn;
	
	@ApiModelProperty(value = "英文简称")
	private String regionShortnameEn;

}