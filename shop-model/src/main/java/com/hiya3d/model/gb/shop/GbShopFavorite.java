package com.hiya3d.model.gb.shop;

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
 * 收藏公司(店铺)
 * @author Rex.Tan
 * @date 2020年8月30日 下午2:07:52
 */
@Data
public class GbShopFavorite implements Serializable {
	 private static final long serialVersionUID = 1L;
    @Id
    @ApiModelProperty(value = "id")
    @Length(max = 32, message = "id输入超出最大长度(32)")
    private String id;

    @ApiModelProperty(value = "店铺ID")
	@Length(max = 32, message = "店铺ID输入超出最大长度(32)")
	@NotBlank(message = "店铺ID不能为空")
	private String shopId;

    @ApiModelProperty(value = "用户ID")
    @Length(max = 32, message = "用户ID输入超出最大长度(32)")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

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