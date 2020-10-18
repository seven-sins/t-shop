package com.hiya3d.mapper.gb.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hiya3d.model.gb.shop.GbTheme;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 公司
 * @author seven sins
 * @date 2020年8月30日 下午12:38:11
 */
@Mapper
public interface GbThemeMapper extends BaseMapper<GbTheme> {

	/**
	 * 列表查询
	 * @author Rex.Tan
	 * @date 2020年10月18日 下午4:21:09
	 * @param gbTheme
	 * @return
	 */
	List<GbTheme> find(GbTheme gbTheme);
}
