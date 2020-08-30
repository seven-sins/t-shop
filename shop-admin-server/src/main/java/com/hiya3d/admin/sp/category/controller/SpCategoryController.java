package com.hiya3d.admin.sp.category.controller;

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
import com.hiya3d.admin.sp.category.service.SpCategoryService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.category.SpCategory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品分类
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品分类")
@RestController
public class SpCategoryController {

	@Autowired
	SpCategoryService spCategoryService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spCategory")
	public Result<List<SpCategory>> list(Page page, SpCategory spCategory) {
		page.start();
		List<SpCategory> list = spCategoryService.find(spCategory);

		return new Result<>(list).total(new PageInfo<SpCategory>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spCategory/{id}")
	public Result<SpCategory> getById(@PathVariable("id") String id) {
		return new Result<>(spCategoryService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spCategory")
	public Result<?> save(@Valid @RequestBody SpCategory spCategory) {
		spCategory.setId(IdMaker.get());
		spCategoryService.save(spCategory);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spCategory/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpCategory spCategory) {
		spCategory.setId(id);
		spCategoryService.updateByIdSelective(spCategory);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spCategory/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spCategoryService.removeById(id);

		return Result.SUCCESS;
	}
}
