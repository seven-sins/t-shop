package com.hiya3d.admin.gb.company.controller;

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
import com.hiya3d.admin.gb.company.service.GbCompanyFavoriteService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.company.GbCompanyFavorite;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 收藏公司(店铺)
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "收藏公司(店铺)")
@RestController
public class GbCompanyFavoriteController {

	@Autowired
	GbCompanyFavoriteService gbCompanyFavoriteService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbCompanyFavorite")
	public Result<List<GbCompanyFavorite>> list(Page page, GbCompanyFavorite gbCompanyFavorite) {
		page.start();
		List<GbCompanyFavorite> list = gbCompanyFavoriteService.find(gbCompanyFavorite);

		return new Result<>(list).total(new PageInfo<GbCompanyFavorite>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbCompanyFavorite/{id}")
	public Result<GbCompanyFavorite> getById(@PathVariable("id") String id) {
		return new Result<>(gbCompanyFavoriteService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbCompanyFavorite")
	public Result<?> save(@Valid @RequestBody GbCompanyFavorite gbCompanyFavorite) {
		gbCompanyFavorite.setId(IdMaker.get());
		gbCompanyFavoriteService.save(gbCompanyFavorite);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbCompanyFavorite/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbCompanyFavorite gbCompanyFavorite) {
		gbCompanyFavorite.setId(id);
		gbCompanyFavoriteService.updateByIdSelective(gbCompanyFavorite);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbCompanyFavorite/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbCompanyFavoriteService.removeById(id);

		return Result.SUCCESS;
	}
}
