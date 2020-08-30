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
import com.hiya3d.admin.sp.category.service.SpGoodsSkuService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.category.SpGoodsSku;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品sku(2级分类)
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品sku(2级分类)")
@RestController
public class SpGoodsSkuController {

	@Autowired
	SpGoodsSkuService spGoodsSkuService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsSku")
	public Result<List<SpGoodsSku>> list(Page page, SpGoodsSku spGoodsSku) {
		page.start();
		List<SpGoodsSku> list = spGoodsSkuService.find(spGoodsSku);

		return new Result<>(list).total(new PageInfo<SpGoodsSku>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsSku/{id}")
	public Result<SpGoodsSku> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsSkuService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsSku")
	public Result<?> save(@Valid @RequestBody SpGoodsSku spGoodsSku) {
		spGoodsSku.setId(IdMaker.get());
		spGoodsSkuService.save(spGoodsSku);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsSku/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsSku spGoodsSku) {
		spGoodsSku.setId(id);
		spGoodsSkuService.updateByIdSelective(spGoodsSku);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsSku/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsSkuService.removeById(id);

		return Result.SUCCESS;
	}
}
