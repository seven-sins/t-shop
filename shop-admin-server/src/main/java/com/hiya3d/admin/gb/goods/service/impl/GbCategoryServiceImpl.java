package com.hiya3d.admin.gb.goods.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hiya3d.admin.gb.goods.service.GbCategoryService;
import com.hiya3d.common.base.service.impl.BaseServiceImpl;
import com.hiya3d.mapper.gb.goods.GbCategoryMapper;
import com.hiya3d.model.gb.goods.GbCategory;
import com.hiya3d.model.gb.goods.vo.GbCategoryVo;

/**
 * 分类
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:18:36
 */
@Service
public class GbCategoryServiceImpl extends BaseServiceImpl<GbCategoryMapper, GbCategory> implements GbCategoryService {
	
	@Override
	public GbCategory getByCategoryName(String categoryName) {
		return baseMapper.getByCategoryName(categoryName);
	}

	@Override
	public List<GbCategoryVo> find(GbCategoryVo gbCategoryVo) {
		return baseMapper.find(gbCategoryVo);
	}

}
