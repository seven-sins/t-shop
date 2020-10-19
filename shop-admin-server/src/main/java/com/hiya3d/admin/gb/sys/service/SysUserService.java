package com.hiya3d.admin.gb.sys.service;

import java.util.List;

import com.hiya3d.common.base.service.BaseService;
import com.hiya3d.model.gb.sys.SysUser;
import com.hiya3d.model.gb.sys.vo.SysUserVo;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:16:56
 */
public interface SysUserService extends BaseService<SysUser> {

	/**
	 * 列表查询s
	 * @author Rex.Tan
	 * @date 2020-10-19 14:22:43
	 * @param sysUser
	 * @return
	 */
	List<SysUserVo> find(SysUserVo sysUser);
}
