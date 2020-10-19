package com.hiya3d.admin.gb.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hiya3d.admin.gb.sys.service.SysRoleService;
import com.hiya3d.common.base.service.impl.BaseServiceImpl;
import com.hiya3d.mapper.gb.sys.SysRoleMapper;
import com.hiya3d.model.gb.sys.SysRole;
import com.hiya3d.model.gb.sys.vo.SysRoleVo;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:18:36
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Override
	public List<SysRoleVo> find(SysRoleVo sysRole) {
		return baseMapper.find(sysRole);
	}

}
