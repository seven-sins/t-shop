package com.hiya3d.admin.gb.sys.service;

import java.util.List;

import com.hiya3d.common.base.service.BaseService;
import com.hiya3d.model.gb.sys.SysRoleMenu;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:16:56
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu> {
	
	/**
	 * 设置角色权限
	 * @author Rex.Tan
	 * @date 2020-10-21 15:29:41
	 * @param roleId
	 * @param roleMenuList
	 */
	void saveBatch(String roleId, List<SysRoleMenu> roleMenuList);

}
