package com.hiya3d.admin.gb.shop.controller;

import java.math.BigDecimal;
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
import com.hiya3d.admin.gb.shop.service.GbShopService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.shop.GbShop;
import com.hiya3d.model.gb.shop.vo.GbShopVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 店铺
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "店铺")
@RestController
public class GbShopController {

	@Autowired
	GbShopService gbShopService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbShop")
	public Result<List<GbShopVo>> list(Page page, GbShopVo gbShopVo) {
		page.start();
		List<GbShopVo> list = gbShopService.find(gbShopVo);

		return new Result<>(list).total(new PageInfo<GbShopVo>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbShop/{id}")
	public Result<GbShop> getById(@PathVariable("id") String id) {
		return new Result<>(gbShopService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbShop")
	public Result<?> save(@Valid @RequestBody GbShop gbShop) {
		gbShop.setId(IdMaker.get());
		gbShop.setSaleQty(0);
		gbShop.setGrade(new BigDecimal("0"));
		gbShop.setLevel(new BigDecimal("0"));
		gbShopService.save(gbShop);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbShop/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbShop gbShop) {
		gbShop.setId(id);
		gbShop.setSaleQty(null);
		gbShop.setGrade(null);
		gbShop.setLevel(null);
		gbShopService.updateByIdSelective(gbShop);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbShop/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbShopService.removeById(id);

		return Result.SUCCESS;
	}
}
