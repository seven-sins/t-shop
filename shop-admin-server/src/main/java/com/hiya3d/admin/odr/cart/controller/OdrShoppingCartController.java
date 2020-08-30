package com.hiya3d.admin.odr.cart.controller;

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
import com.hiya3d.admin.odr.cart.service.OdrShoppingCartService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.odr.cart.OdrShoppingCart;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 购物车
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "购物车")
@RestController
public class OdrShoppingCartController {

	@Autowired
	OdrShoppingCartService odrShoppingCartService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/odrShoppingCart")
	public Result<List<OdrShoppingCart>> list(Page page, OdrShoppingCart odrShoppingCart) {
		page.start();
		List<OdrShoppingCart> list = odrShoppingCartService.find(odrShoppingCart);

		return new Result<>(list).total(new PageInfo<OdrShoppingCart>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/odrShoppingCart/{id}")
	public Result<OdrShoppingCart> getById(@PathVariable("id") String id) {
		return new Result<>(odrShoppingCartService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/odrShoppingCart")
	public Result<?> save(@Valid @RequestBody OdrShoppingCart odrShoppingCart) {
		odrShoppingCart.setId(IdMaker.get());
		odrShoppingCartService.save(odrShoppingCart);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/odrShoppingCart/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody OdrShoppingCart odrShoppingCart) {
		odrShoppingCart.setId(id);
		odrShoppingCartService.updateByIdSelective(odrShoppingCart);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/odrShoppingCart/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		odrShoppingCartService.removeById(id);

		return Result.SUCCESS;
	}
}
