package com.hiya3d.admin.gb.sys.service;

import java.util.List;

import com.hiya3d.common.base.service.BaseService;
import com.hiya3d.model.gb.sys.SysUserRole;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:16:56
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {
	
	/**
	 * 批量新增s
	 * @author Rex.Tan
	 * @date 2020-10-21 10:43:59
	 * @param userId
	 * @param userRoleList
	 */
	void saveBatch(String userId, List<SysUserRole> userRoleList);

}
