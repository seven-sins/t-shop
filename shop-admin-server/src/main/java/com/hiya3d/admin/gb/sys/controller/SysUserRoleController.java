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
import com.hiya3d.admin.gb.sys.service.SysUserRoleService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.model.gb.sys.SysUserRole;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户角色
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "用户角色")
@RestController
public class SysUserRoleController {

	@Autowired
	SysUserRoleService sysUserRoleService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/sysUserRole")
	public Result<List<SysUserRole>> list(Page page, SysUserRole sysUserRole) {
		//		page.start();
		List<SysUserRole> list = sysUserRoleService.find(sysUserRole);

		return new Result<>(list).total(new PageInfo<SysUserRole>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/sysUserRole/{id}")
	public Result<SysUserRole> getById(@PathVariable("id") String id) {
		return new Result<>(sysUserRoleService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/sysUserRole/saveBatch/{userId}")
	public Result<?> save(@PathVariable("userId") String userId, @RequestBody List<SysUserRole> userRoleList) {
		sysUserRoleService.saveBatch(userId, userRoleList);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/sysUserRole/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SysUserRole sysUserRole) {
		sysUserRole.setId(id);
		sysUserRoleService.updateByIdSelective(sysUserRole);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/sysUserRole/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		sysUserRoleService.removeById(id);

		return Result.SUCCESS;
	}
}
