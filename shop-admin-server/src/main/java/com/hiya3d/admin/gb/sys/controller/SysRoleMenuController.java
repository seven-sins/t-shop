package com.hiya3d.admin.gb.sys.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hiya3d.admin.gb.sys.service.SysRoleMenuService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.sys.SysRoleMenu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色菜单
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "角色菜单")
@RestController
public class SysRoleMenuController {

	@Autowired
	SysRoleMenuService sysRoleMenuService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/sysRoleMenu")
	public Result<List<SysRoleMenu>> list(Page page, SysRoleMenu sysRoleMenu) {
		page.start();
		List<SysRoleMenu> list = sysRoleMenuService.find(sysRoleMenu);

		return new Result<>(list).total(new PageInfo<SysRoleMenu>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/sysRoleMenu/{id}")
	public Result<SysRoleMenu> getById(@PathVariable("id") String id) {
		return new Result<>(sysRoleMenuService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/sysRoleMenu")
	public Result<?> save(@Valid @RequestBody SysRoleMenu sysRoleMenu) {
		sysRoleMenu.setId(IdMaker.get());
		sysRoleMenuService.save(sysRoleMenu);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/sysRoleMenu/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SysRoleMenu sysRoleMenu) {
		sysRoleMenu.setId(id);
		sysRoleMenuService.updateByIdSelective(sysRoleMenu);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/sysRoleMenu/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		sysRoleMenuService.removeById(id);

		return Result.SUCCESS;
	}
}
