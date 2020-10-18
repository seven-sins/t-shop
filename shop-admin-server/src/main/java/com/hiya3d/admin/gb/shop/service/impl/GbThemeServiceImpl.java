package com.hiya3d.admin.gb.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hiya3d.admin.gb.shop.service.GbThemeService;
import com.hiya3d.common.base.service.impl.BaseServiceImpl;
import com.hiya3d.mapper.gb.shop.GbThemeMapper;
import com.hiya3d.model.gb.shop.GbTheme;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:18:36
 */
@Service
public class GbThemeServiceImpl extends BaseServiceImpl<GbThemeMapper, GbTheme> implements GbThemeService {

	@Override
	public List<GbTheme> find(GbTheme entity) {
		return baseMapper.find(entity);
	}
}
