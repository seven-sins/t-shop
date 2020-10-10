package com.hiya3d.admin.gb.shop.controller;

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
import com.hiya3d.admin.gb.shop.service.GbSystemService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.shop.GbSystem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 公司
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "系统参数")
@RestController
public class GbSystemController {

	@Autowired
	GbSystemService gbSystemService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbSystem")
	public Result<List<GbSystem>> list(Page page, GbSystem gbSystem) {
		page.start();
		List<GbSystem> list = gbSystemService.find(gbSystem);

		return new Result<>(list).total(new PageInfo<GbSystem>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbSystem/{id}")
	public Result<GbSystem> getById(@PathVariable("id") String id) {
		return new Result<>(gbSystemService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbSystem")
	public Result<?> save(@Valid @RequestBody GbSystem gbSystem) {
		gbSystem.setId(IdMaker.get());
		gbSystemService.save(gbSystem);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbSystem/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbSystem gbSystem) {
		gbSystem.setId(id);
		gbSystemService.updateByIdSelective(gbSystem);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbSystem/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbSystemService.removeById(id);

		return Result.SUCCESS;
	}
}
