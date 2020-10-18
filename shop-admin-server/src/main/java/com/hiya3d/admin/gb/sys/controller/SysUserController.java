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
import com.hiya3d.admin.gb.sys.service.SysUserService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.sys.SysUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "用户")
@RestController
public class SysUserController {

	@Autowired
	SysUserService sysUserService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/sysUser")
	public Result<List<SysUser>> list(Page page, SysUser sysUser) {
		page.start();
		List<SysUser> list = sysUserService.find(sysUser);
		return new Result<>(list).total(new PageInfo<SysUser>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/sysUser/{id}")
	public Result<SysUser> getById(@PathVariable("id") String id) {
		return new Result<>(sysUserService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/sysUser")
	public Result<?> save(@Valid @RequestBody SysUser sysUser) {
		sysUser.setId(IdMaker.get());
		sysUserService.save(sysUser);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/sysUser/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SysUser sysUser) {
		sysUser.setId(id);
		sysUserService.updateByIdSelective(sysUser);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/sysUser/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		sysUserService.removeById(id);

		return Result.SUCCESS;
	}
}
