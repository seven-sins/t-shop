package com.hiya3d.admin.gb.goods.service;

import java.util.List;

import com.hiya3d.common.base.service.BaseService;
import com.hiya3d.model.gb.goods.GbCategory;
import com.hiya3d.model.gb.goods.vo.GbCategoryVo;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:16:56
 */
public interface GbCategoryService extends BaseService<GbCategory> {
	
	/**
	 * 根据名称查询
	 * @author Rex.Tan
	 * @date 2020-10-20 10:14:04
	 * @param categoryName
	 * @return
	 */
	GbCategory getByCategoryName(String categoryName);
	
	/**
	 * 列表查询
	 * @author Rex.Tan
	 * @date 2020-10-20 10:49:17
	 * @param gbCategoryVo
	 * @return
	 */
	List<GbCategoryVo> find(GbCategoryVo gbCategoryVo);
}
