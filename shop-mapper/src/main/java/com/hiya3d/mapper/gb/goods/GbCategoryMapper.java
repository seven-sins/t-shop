package com.hiya3d.mapper.gb.goods;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hiya3d.model.gb.goods.GbCategory;
import com.hiya3d.model.gb.goods.vo.GbCategoryVo;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 商品分类
 * @author seven sins
 * @date 2020年8月30日 下午12:38:11
 */
@Mapper
public interface GbCategoryMapper extends BaseMapper<GbCategory> {

	/**
	 * 根据分类名称查询单条记录
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
