package com.hiya3d.admin.sp.goods.controller;

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
import com.hiya3d.admin.sp.goods.service.SpGoodsFavoriteService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.goods.SpGoodsFavorite;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 收藏商品
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "收藏商品")
@RestController
public class SpGoodsFavoriteController {

	@Autowired
	SpGoodsFavoriteService spGoodsFavoriteService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsFavorite")
	public Result<List<SpGoodsFavorite>> list(Page page, SpGoodsFavorite spGoodsFavorite) {
		page.start();
		List<SpGoodsFavorite> list = spGoodsFavoriteService.find(spGoodsFavorite);

		return new Result<>(list).total(new PageInfo<SpGoodsFavorite>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsFavorite/{id}")
	public Result<SpGoodsFavorite> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsFavoriteService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsFavorite")
	public Result<?> save(@Valid @RequestBody SpGoodsFavorite spGoodsFavorite) {
		spGoodsFavorite.setId(IdMaker.get());
		spGoodsFavoriteService.save(spGoodsFavorite);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsFavorite/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsFavorite spGoodsFavorite) {
		spGoodsFavorite.setId(id);
		spGoodsFavoriteService.updateByIdSelective(spGoodsFavorite);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsFavorite/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsFavoriteService.removeById(id);

		return Result.SUCCESS;
	}
}
