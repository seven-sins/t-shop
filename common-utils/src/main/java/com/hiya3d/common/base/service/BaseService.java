package com.hiya3d.common.base.service;

import java.io.Serializable;
import java.util.List;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 公共方法
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:04:31
 */
public interface BaseService<T> {
	
	/**
	 * 列表查询
	 * @param entity 实体对象
	 */
	List<T> find(T entity);
	/**
     * 保存
     * @param entity 实体对象
     */
    void save(T entity);

    /**
     * 根据 ID 删除
     * @param id 主键ID
     */
    void removeById(Serializable id);

    /**
     * 根据 ID 选择修改(全字段修改)
     * @param entity 实体对象
     */
    void updateById(T entity);
    
    /**
     * 根据 ID 选择修改(只修改非null字段)
     * @param entity 实体对象
     */
    void updateByIdSelective(T entity);

    /**
     * 根据 ID 查询
     * @param id 主键ID
     */
    T getById(Serializable id);
   
    /**
     * 获取对应 entity 的 BaseMapper
     */
    BaseMapper<T> getBaseMapper();
}
