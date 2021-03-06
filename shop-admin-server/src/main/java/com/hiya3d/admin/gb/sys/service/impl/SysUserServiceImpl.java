package com.hiya3d.admin.gb.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hiya3d.admin.gb.sys.service.SysUserService;
import com.hiya3d.common.base.service.impl.BaseServiceImpl;
import com.hiya3d.mapper.gb.sys.SysUserMapper;
import com.hiya3d.model.gb.sys.SysUser;
import com.hiya3d.model.gb.sys.vo.SysUserVo;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:18:36
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Override
	public List<SysUserVo> find(SysUserVo entity) {
		return baseMapper.find(entity);
	}
}
