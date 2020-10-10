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
import com.hiya3d.admin.gb.shop.service.GbShopLogisticsService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.shop.GbShopLogistics;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 店铺物流
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "店铺物流")
@RestController
public class GbShopLogisticsController {

	@Autowired
	GbShopLogisticsService gbShopLogisticsService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbShopLogistics")
	public Result<List<GbShopLogistics>> list(Page page, GbShopLogistics gbShopLogistics) {
		page.start();
		List<GbShopLogistics> list = gbShopLogisticsService.find(gbShopLogistics);

		return new Result<>(list).total(new PageInfo<GbShopLogistics>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbShopLogistics/{id}")
	public Result<GbShopLogistics> getById(@PathVariable("id") String id) {
		return new Result<>(gbShopLogisticsService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbShopLogistics")
	public Result<?> save(@Valid @RequestBody GbShopLogistics gbShopLogistics) {
		gbShopLogistics.setId(IdMaker.get());
		gbShopLogisticsService.save(gbShopLogistics);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbShopLogistics/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbShopLogistics gbShopLogistics) {
		gbShopLogistics.setId(id);
		gbShopLogisticsService.updateByIdSelective(gbShopLogistics);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbShopLogistics/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbShopLogisticsService.removeById(id);

		return Result.SUCCESS;
	}
}
