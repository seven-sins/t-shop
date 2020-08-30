package com.hiya3d.common.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hiya3d.common.base.service.BaseService;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * service公共方法
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:15:24
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {

	@Autowired
	protected M baseMapper;

	@Override
	public M getBaseMapper() {
		return baseMapper;
	}

	@Override
	public List<T> find(T entity) {
		return baseMapper.select(entity);
	}

	@Override
	public void save(T entity) {
		baseMapper.insert(entity);
	}

	@Override
	public void removeById(Serializable id) {
		baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateById(T entity) {
		baseMapper.updateByPrimaryKey(entity);
	}

	@Override
	public void updateByIdSelective(T entity) {
		baseMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public T getById(Serializable id) {
		return baseMapper.selectByPrimaryKey(id);
	}

}
