package com.hiya3d.admin.gb.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hiya3d.admin.gb.shop.service.GbShopService;
import com.hiya3d.common.base.service.impl.BaseServiceImpl;
import com.hiya3d.mapper.gb.shop.GbShopMapper;
import com.hiya3d.model.gb.shop.GbShop;
import com.hiya3d.model.gb.shop.vo.GbShopVo;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:18:36
 */
@Service
public class GbShopServiceImpl extends BaseServiceImpl<GbShopMapper, GbShop> implements GbShopService {

	@Override
	public List<GbShopVo> find(GbShopVo gbShopVo) {
		return this.baseMapper.find(gbShopVo);
	}

}
