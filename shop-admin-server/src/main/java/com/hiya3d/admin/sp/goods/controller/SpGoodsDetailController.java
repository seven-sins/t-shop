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
import com.hiya3d.admin.sp.goods.service.SpGoodsDetailService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.model.sp.goods.SpGoodsDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品详情
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品详情")
@RestController
public class SpGoodsDetailController {

	@Autowired
	SpGoodsDetailService spGoodsDetailService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsDetail")
	public Result<List<SpGoodsDetail>> list(Page page, SpGoodsDetail spGoodsDetail) {
		page.start();
		List<SpGoodsDetail> list = spGoodsDetailService.find(spGoodsDetail);

		return new Result<>(list).total(new PageInfo<SpGoodsDetail>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsDetail/{id}")
	public Result<SpGoodsDetail> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsDetailService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsDetail")
	public Result<?> save(@Valid @RequestBody SpGoodsDetail spGoodsDetail) {
		spGoodsDetailService.save(spGoodsDetail);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsDetail/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsDetail spGoodsDetail) {
		spGoodsDetailService.updateByIdSelective(spGoodsDetail);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsDetail/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsDetailService.removeById(id);

		return Result.SUCCESS;
	}
}
