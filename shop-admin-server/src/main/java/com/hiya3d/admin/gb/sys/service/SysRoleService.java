package com.hiya3d.admin.gb.sys.service;

import java.util.List;

import com.hiya3d.common.base.service.BaseService;
import com.hiya3d.model.gb.sys.SysRole;
import com.hiya3d.model.gb.sys.vo.SysRoleVo;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:16:56
 */
public interface SysRoleService extends BaseService<SysRole> {

	/**
	 * 列表查询
	 * @author Rex.Tan
	 * @date 2020-10-19 14:50:35
	 * @param sysRole
	 * @return
	 */
	List<SysRoleVo> find(SysRoleVo sysRole);
}
