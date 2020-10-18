package com.hiya3d.admin.gb.shop.service;

import java.util.List;

import com.hiya3d.common.base.service.BaseService;
import com.hiya3d.model.gb.shop.GbShop;
import com.hiya3d.model.gb.shop.vo.GbShopVo;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:16:56
 */
public interface GbShopService extends BaseService<GbShop> {

	/**
	 * 列表查询
	 * @author Rex.Tan
	 * @date 2020年10月18日 下午9:07:24
	 * @param gbShopVo
	 * @return
	 */
	List<GbShopVo> find(GbShopVo gbShopVo);
}
