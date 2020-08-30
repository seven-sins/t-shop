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
import com.hiya3d.admin.gb.sys.service.SysRoleService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.sys.SysRole;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "角色")
@RestController
public class SysRoleController {

	@Autowired
	SysRoleService sysRoleService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/sysRole")
	public Result<List<SysRole>> list(Page page, SysRole sysRole) {
		page.start();
		List<SysRole> list = sysRoleService.find(sysRole);

		return new Result<>(list).total(new PageInfo<SysRole>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/sysRole/{id}")
	public Result<SysRole> getById(@PathVariable("id") String id) {
		return new Result<>(sysRoleService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/sysRole")
	public Result<?> save(@Valid @RequestBody SysRole sysRole) {
		sysRole.setId(IdMaker.get());
		sysRoleService.save(sysRole);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/sysRole/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SysRole sysRole) {
		sysRole.setId(id);
		sysRoleService.updateByIdSelective(sysRole);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/sysRole/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		sysRoleService.removeById(id);

		return Result.SUCCESS;
	}
}
