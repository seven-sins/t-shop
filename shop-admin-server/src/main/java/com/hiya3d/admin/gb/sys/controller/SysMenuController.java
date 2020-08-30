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
import com.hiya3d.admin.gb.sys.service.SysMenuService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.sys.SysMenu;

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

	@ApiOperation(value = "列表查询")
	@GetMapping("/sysMenu")
	public Result<List<SysMenu>> list(Page page, SysMenu sysMenu) {
		page.start();
		List<SysMenu> list = sysMenuService.find(sysMenu);

		return new Result<>(list).total(new PageInfo<SysMenu>(list).getTotal());
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
}
