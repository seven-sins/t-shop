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
import com.hiya3d.admin.gb.shop.service.GbShopServiceService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.shop.GbShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 店铺
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "店铺客服")
@RestController
public class GbShopServiceController {

	@Autowired
	GbShopServiceService gbShopServiceService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbShopService")
	public Result<List<GbShopService>> list(Page page, GbShopService gbShopService) {
		page.start();
		List<GbShopService> list = gbShopServiceService.find(gbShopService);

		return new Result<>(list).total(new PageInfo<GbShopService>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbShopService/{id}")
	public Result<GbShopService> getById(@PathVariable("id") String id) {
		return new Result<>(gbShopServiceService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbShopService")
	public Result<?> save(@Valid @RequestBody GbShopService gbShopService) {
		gbShopService.setId(IdMaker.get());
		gbShopServiceService.save(gbShopService);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbShopService/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbShopService gbShopService) {
		gbShopService.setId(id);
		gbShopServiceService.updateByIdSelective(gbShopService);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbShopService/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbShopServiceService.removeById(id);

		return Result.SUCCESS;
	}
}
