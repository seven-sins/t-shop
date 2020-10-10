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
import com.hiya3d.admin.gb.shop.service.GbShopFavoriteService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.shop.GbShopFavorite;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 收藏店铺
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "收藏店铺")
@RestController
public class GbShopFavoriteController {

	@Autowired
	GbShopFavoriteService gbShopFavoriteService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbShopFavorite")
	public Result<List<GbShopFavorite>> list(Page page, GbShopFavorite gbShopFavorite) {
		page.start();
		List<GbShopFavorite> list = gbShopFavoriteService.find(gbShopFavorite);

		return new Result<>(list).total(new PageInfo<GbShopFavorite>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbShopFavorite/{id}")
	public Result<GbShopFavorite> getById(@PathVariable("id") String id) {
		return new Result<>(gbShopFavoriteService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbShopFavorite")
	public Result<?> save(@Valid @RequestBody GbShopFavorite gbShopFavorite) {
		gbShopFavorite.setId(IdMaker.get());
		gbShopFavoriteService.save(gbShopFavorite);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbShopFavorite/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbShopFavorite gbShopFavorite) {
		gbShopFavorite.setId(id);
		gbShopFavoriteService.updateByIdSelective(gbShopFavorite);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbShopFavorite/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbShopFavoriteService.removeById(id);

		return Result.SUCCESS;
	}
}
