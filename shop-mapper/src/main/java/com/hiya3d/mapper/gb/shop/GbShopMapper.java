package com.hiya3d.mapper.gb.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hiya3d.model.gb.shop.GbShop;
import com.hiya3d.model.gb.shop.vo.GbShopVo;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 店铺
 * @author Rex.Tan
 * @date 2020年8月30日 下午12:38:11
 */
@Mapper
public interface GbShopMapper extends BaseMapper<GbShop> {

	/**
	 * 列表查询
	 * @author Rex.Tan
	 * @date 2020年10月18日 下午9:07:24
	 * @param gbShopVo
	 * @return
	 */
	List<GbShopVo> find(GbShopVo gbShopVo);
}
