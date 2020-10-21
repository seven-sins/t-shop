package com.hiya3d.admin.gb.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hiya3d.admin.gb.sys.service.SysRoleMenuService;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.common.base.service.impl.BaseServiceImpl;
import com.hiya3d.common.conf.user.context.UserContext;
import com.hiya3d.common.utils.ValidationUtil;
import com.hiya3d.mapper.gb.sys.SysRoleMenuMapper;
import com.hiya3d.model.gb.sys.SysRoleMenu;

/**
 * 公司
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:18:36
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

	@Override
	public void saveBatch(String roleId, List<SysRoleMenu> roleMenuList) {
		/**
		 * 1.先删除已配置的角色
		 */
		baseMapper.deleteByRoleId(roleId);
		/**
		 * 2.批量保存
		 */
		if(roleMenuList == null || roleMenuList.isEmpty()) {
			return;
		}
		ValidationUtil.validList(roleMenuList);
		Date now = new Date();
		roleMenuList.forEach(item -> {
			item.setId(IdMaker.get());
			item.setShopId(UserContext.get().getShopId());
			item.setIsDisabled(0);
			item.setIsDeleted(0);
			item.setCreatedBy(UserContext.get().getUserName());
			item.setCreatedTime(now);
			item.setCreatedUserId(UserContext.get().getUserId());
		});
		baseMapper.saveBatch(roleMenuList);
	}

}
