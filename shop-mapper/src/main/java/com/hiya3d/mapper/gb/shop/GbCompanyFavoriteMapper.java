package com.hiya3d.mapper.gb.shop;

import org.apache.ibatis.annotations.Mapper;

import com.hiya3d.model.gb.shop.GbShopFavorite;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 公司
 * @author seven sins
 * @date 2020年8月30日 下午12:38:11
 */
@Mapper
public interface GbCompanyFavoriteMapper extends BaseMapper<GbShopFavorite> {

}
