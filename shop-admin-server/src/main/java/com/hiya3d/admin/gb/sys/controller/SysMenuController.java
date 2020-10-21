package com.hiya3d.admin.gb.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hiya3d.admin.gb.sys.service.SysMenuService;
import com.hiya3d.admin.gb.sys.service.SysRoleMenuService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.common.utils.Assert;
import com.hiya3d.model.gb.sys.SysMenu;
import com.hiya3d.model.gb.sys.SysRoleMenu;
import com.hiya3d.model.gb.sys.vo.SysMenuVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "菜单")
@RestController
public class SysMenuController {

	@Autowired
	SysMenuService sysMenuService;
	@Autowired
	SysRoleMenuService sysRoleMenuService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/sysMenu")
	public Result<List<SysMenuVo>> list(Page page, SysMenu sysMenu) {
		List<SysMenu> list = sysMenuService.find(sysMenu);
		List<SysMenuVo> treeList = new ArrayList<>();
		this.refactory(list, treeList);

		return new Result<>(treeList);
	}
	
	@ApiOperation(value = "角色权限列表查询")
	@GetMapping("/sysMenu/{roleId}/menuList")
	public Result<List<SysMenuVo>> queryRoleMenuList(@PathVariable("roleId") String roleId, SysMenu sysMenu) {
		List<SysMenu> list = sysMenuService.find(sysMenu);
		SysRoleMenu param = new SysRoleMenu();
		param.setRoleId(roleId);
		List<SysRoleMenu> roleMenuList = sysRoleMenuService.find(param);
		List<SysMenuVo> treeList = new ArrayList<>();
		this.refactory(list, treeList);
		// 更新数据选中状态
		this.updateCheckStatus(treeList, roleMenuList);

		return new Result<>(treeList);
	}
	
	/**
	 * 更新数据选中状态
	 * @author Rex.Tan
	 * @date 2020-10-21 17:36:54
	 * @param treeList
	 * @param roleMenuList
	 */
	private void updateCheckStatus(List<SysMenuVo> treeList, List<SysRoleMenu> roleMenuList) {
		if(roleMenuList == null || roleMenuList.isEmpty()) {
			return;
		}
		for(SysMenuVo item: treeList) {
			for(SysRoleMenu roleMenu: roleMenuList) {
				if(item.getId().equals(roleMenu.getMenuId())) {
					item.setIsSelect(true);
				}
			}
			if(item.getChildren() != null && !item.getChildren().isEmpty()) {
				this.updateCheckStatus(item.getChildren(), roleMenuList);
			}
		}
	}

	/**
	 * 将菜单列表转为树形结构
	 * @author Rex.Tan
	 * @date 2020-10-19 17:06:34
	 * @param list
	 * @param treeList
	 */
	private void refactory(List<SysMenu> list, List<SysMenuVo> treeList){
		if(list == null || list.isEmpty()) {
			return;
		}
		for(SysMenu item: list) {
			if(StringUtils.isBlank(item.getParentId())) {
				SysMenuVo menuVo = new SysMenuVo();
				BeanUtils.copyProperties(item, menuVo);
				menuVo.setText(menuVo.getMenuName());
				// menuVo.setIconCls(menuVo.getIcon());
				menuVo.setChildren(this.getChildren(list, menuVo.getId()));
				treeList.add(menuVo);
			}
		}
	}
	
	private List<SysMenuVo> getChildren(List<SysMenu> list, String parentId){
		List<SysMenuVo> children = new ArrayList<>();
		for(SysMenu item: list) {
			if(parentId.equals(item.getParentId())) {
				SysMenuVo menuVo = new SysMenuVo();
				BeanUtils.copyProperties(item,  menuVo);
				menuVo.setText(menuVo.getMenuName());
				// menuVo.setIconCls(menuVo.getIcon());
				menuVo.setChildren(this.getChildren(list, menuVo.getId()));
				children.add(menuVo);
			}
		}
		
		return children.isEmpty() ? null : children;
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/sysMenu/{id}")
	public Result<SysMenu> getById(@PathVariable("id") String id) {
		return new Result<>(sysMenuService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/sysMenu")
	public Result<?> save(@Valid @RequestBody SysMenu sysMenu) {
		sysMenu.setId(IdMaker.get());
		sysMenuService.save(sysMenu);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/sysMenu/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SysMenu sysMenu) {
		this.checkLogic(id, sysMenu.getParentId());
		sysMenu.setId(id);
		sysMenuService.updateByIdSelective(sysMenu);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/sysMenu/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		sysMenuService.removeById(id);

		return Result.SUCCESS;
	}
	
	/**
	 * 检查数据逻辑, 上级节点不能选择当前节点以及下级节点
	 * @author Rex.Tan
	 * @date 2020-10-20 13:44:38
	 * @param id
	 * @param parentId
	 */
	private void checkLogic(String id, String parentId) {
		if(StringUtils.isBlank(parentId)) {
			return;
		}
		Assert.isTrue(!id.equals(parentId), "上级分类不能选择当前节点");
		List<SysMenu> list = sysMenuService.find(new SysMenuVo());
		List<SysMenuVo> treeList = new ArrayList<>();
		this.refactory(list, treeList);
		
		this.checkLogic(id, parentId, treeList);
	}
	
	private void checkLogic(String id, String parentId, List<SysMenuVo> list) {
		for(SysMenuVo item: list) {
			if(id.equals(item.getId())) {
				checkLogic(parentId, item.getChildren());
			}
		}
	}
	
	private void checkLogic(String parentId, List<SysMenuVo> list) {
		if(list == null) {
			return;
		}
		for(SysMenuVo item: list) {
			Assert.isTrue(!parentId.equals(item.getId()), "上级节点不能选择下级节点");
			checkLogic(parentId, item.getChildren());
		}
	}
}
