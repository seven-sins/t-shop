package com.hiya3d.mapper.gb.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hiya3d.model.gb.sys.SysRoleMenu;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 角色权限
 * @author seven sins
 * @date 2020年8月30日 下午12:38:11
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

	/**
	 * 删除角色已配置的菜单
	 * @author Rex.Tan
	 * @date 2020-10-21 10:25:14
	 * @param roleId
	 */
	void deleteByRoleId(String roleId);
	
	/**
	 * 批量新增
	 * @author Rex.Tan
	 * @date 2020-10-21 10:24:23
	 * @param roleMenuList
	 */
	void saveBatch(List<SysRoleMenu> roleMenuList);
}
