package com.hiya3d.mapper.sp.category;

import org.apache.ibatis.annotations.Mapper;

import com.hiya3d.model.sp.category.SpCategory;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 公司
 * @author seven sins
 * @date 2020年8月30日 下午12:38:11
 */
@Mapper
public interface SpCategoryMapper extends BaseMapper<SpCategory> {

}
