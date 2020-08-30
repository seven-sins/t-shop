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
import com.hiya3d.admin.sp.category.service.SpGoodsColorService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.category.SpGoodsColor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品颜色(1级分类)
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品颜色(1级分类)")
@RestController
public class SpGoodsColorController {

	@Autowired
	SpGoodsColorService spGoodsColorService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsColor")
	public Result<List<SpGoodsColor>> list(Page page, SpGoodsColor spGoodsColor) {
		page.start();
		List<SpGoodsColor> list = spGoodsColorService.find(spGoodsColor);

		return new Result<>(list).total(new PageInfo<SpGoodsColor>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsColor/{id}")
	public Result<SpGoodsColor> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsColorService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsColor")
	public Result<?> save(@Valid @RequestBody SpGoodsColor spGoodsColor) {
		spGoodsColor.setId(IdMaker.get());
		spGoodsColorService.save(spGoodsColor);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsColor/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsColor spGoodsColor) {
		spGoodsColor.setId(id);
		spGoodsColorService.updateByIdSelective(spGoodsColor);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsColor/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsColorService.removeById(id);

		return Result.SUCCESS;
	}
}
