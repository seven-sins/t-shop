package com.hiya3d.admin.gb.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hiya3d.admin.gb.sys.service.SysUserRoleService;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.common.base.service.impl.BaseServiceImpl;
import com.hiya3d.common.conf.user.context.UserContext;
import com.hiya3d.common.utils.ValidationUtil;
import com.hiya3d.mapper.gb.sys.SysUserRoleMapper;
import com.hiya3d.model.gb.sys.SysUserRole;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:18:36
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

	@Override
	public void saveBatch(String userId, List<SysUserRole> userRoleList) {
		/**
		 * 1.先删除已配置的角色
		 */
		baseMapper.deleteByUserId(userId);
		/**
		 * 2.批量保存
		 */
		if(userRoleList == null || userRoleList.isEmpty()) {
			return;
		}
		ValidationUtil.validList(userRoleList);
		Date now = new Date();
		userRoleList.forEach(item -> {
			item.setId(IdMaker.get());
			item.setShopId(UserContext.get().getShopId());
			item.setIsDisabled(0);
			item.setCreatedBy(UserContext.get().getUserName());
			item.setCreatedTime(now);
			item.setCreatedUserId(UserContext.get().getUserId());
		});
		baseMapper.saveBatch(userRoleList);
	}

}
