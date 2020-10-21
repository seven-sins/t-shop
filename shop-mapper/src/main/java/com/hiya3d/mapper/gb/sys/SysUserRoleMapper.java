package com.hiya3d.mapper.gb.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hiya3d.model.gb.sys.SysUserRole;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 公司
 * @author seven sins
 * @date 2020年8月30日 下午12:38:11
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
	
	/**
	 * 删除用户已配置的角色
	 * @author Rex.Tan
	 * @date 2020-10-21 10:25:14
	 * @param userId
	 */
	void deleteByUserId(String userId);
	
	/**
	 * 批量新增
	 * @author Rex.Tan
	 * @date 2020-10-21 10:24:23
	 * @param userRoleList
	 */
	void saveBatch(List<SysUserRole> userRoleList);
}
