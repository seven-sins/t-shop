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
import com.hiya3d.admin.sp.goods.service.SpGoodsService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.goods.SpGoods;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品")
@RestController
public class SpGoodsController {

	@Autowired
	SpGoodsService spGoodsService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoods")
	public Result<List<SpGoods>> list(Page page, SpGoods spGoods) {
		page.start();
		List<SpGoods> list = spGoodsService.find(spGoods);

		return new Result<>(list).total(new PageInfo<SpGoods>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoods/{id}")
	public Result<SpGoods> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoods")
	public Result<?> save(@Valid @RequestBody SpGoods spGoods) {
		spGoods.setId(IdMaker.get());
		spGoods.setSaleQty(0);
		spGoodsService.save(spGoods);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoods/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoods spGoods) {
		spGoods.setId(id);
		spGoodsService.updateByIdSelective(spGoods);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoods/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsService.removeById(id);

		return Result.SUCCESS;
	}
}
