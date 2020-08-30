package com.hiya3d.model.sp.comment;

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
 * 评论
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:36:27
 */
@Data
public class SpGoodsComment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(value = "id")
	@Length(max = 32, message = "id输入超出最大长度(32)")
	private String id;

	@ApiModelProperty(value = "商品ID")
	@Length(max = 32, message = "商品ID输入超出最大长度(32)")
	@NotBlank(message = "商品ID不能为空")
	private String goodsId;

	@ApiModelProperty(value = "用户ID")
	@Length(max = 32, message = "用户ID输入超出最大长度(32)")
	@NotBlank(message = "用户ID不能为空")
	private String userId;

	@ApiModelProperty(value = "评论内容")
	@Length(max = 500, message = "评论内容输入超出最大长度(500)")
	private String commentContent;

	@ApiModelProperty(value = "类型(1:好评,2:中评,3:差评,4:追评)")
	@IntValue(message = "类型(1:好评,2:中评,3:差评,4:追评)取值超出范围")
	@NotNull(message = "类型(1:好评,2:中评,3:差评,4:追评)不能为空")
	private Integer commentType;

	@ApiModelProperty(value = "点赞数")
	@IntValue(message = "点赞数取值超出范围")
	@NotNull(message = "点赞数不能为空")
	private Integer likeQty;

	@ApiModelProperty(value = "评论是否有图片(1:是,0:否)")
	@IntValue(message = "评论是否有图片(1:是,0:否)取值超出范围")
	@NotNull(message = "评论是否有图片(1:是,0:否)不能为空")
	private Integer hasImg;

	@ApiModelProperty(value = "订单简单描述(购买分类等)")
	@Length(max = 100, message = "订单简单描述(购买分类等)输入超出最大长度(100)")
	private String orderDesc;

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