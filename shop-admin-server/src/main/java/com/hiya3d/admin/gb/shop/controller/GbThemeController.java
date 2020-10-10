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
import com.hiya3d.admin.gb.shop.service.GbThemeService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.shop.GbTheme;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 公司
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "主题")
@RestController
public class GbThemeController {

	@Autowired
	GbThemeService gbThemeService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbTheme")
	public Result<List<GbTheme>> list(Page page, GbTheme gbTheme) {
		page.start();
		List<GbTheme> list = gbThemeService.find(gbTheme);

		return new Result<>(list).total(new PageInfo<GbTheme>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbTheme/{id}")
	public Result<GbTheme> getById(@PathVariable("id") String id) {
		return new Result<>(gbThemeService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbTheme")
	public Result<?> save(@Valid @RequestBody GbTheme gbTheme) {
		gbTheme.setId(IdMaker.get());
		gbThemeService.save(gbTheme);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbTheme/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbTheme gbTheme) {
		gbTheme.setId(id);
		gbThemeService.updateByIdSelective(gbTheme);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbTheme/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbThemeService.removeById(id);

		return Result.SUCCESS;
	}
}
