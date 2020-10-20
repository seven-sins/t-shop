package com.hiya3d.model.gb.goods;

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
 * 分类
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:31:34
 */
@Data
public class GbCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(value = "id")
	@Length(max = 32, message = "id输入超出最大长度(32)")
	private String id;

	@ApiModelProperty(value = "分类名称")
	@Length(max = 50, message = "分类名称输入超出最大长度(50)")
	@NotBlank(message = "分类名称不能为空")
	private String categoryName;

	@ApiModelProperty(value = "分类编码")
	@Length(max = 50, message = "分类编码输入超出最大长度(50)")
	@NotBlank(message = "分类编码不能为空")
	private String categoryCode;
	
	@ApiModelProperty(value = "排序")
	@Order("ASC")
	private Integer sort;
	
	@ApiModelProperty(value = "主题")
	@Length(max = 32, message = "主题输入超出最大长度(32)")
	private String themeId;

	@ApiModelProperty(value = "备注")
	@Length(max = 500, message = "备注输入超出最大长度(500)")
	private String remark;

	@ApiModelProperty(value = "上级分类")
	@Length(max = 32, message = "上级分类输入超出最大长度(32)")
	private String parentId;

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