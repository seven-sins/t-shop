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
import com.hiya3d.admin.sp.goods.service.SpGoodsParamsDetailService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.goods.SpGoodsParamsDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品参数详情
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品参数详情")
@RestController
public class SpGoodsParamsDetailController {

	@Autowired
	SpGoodsParamsDetailService spGoodsParamsDetailService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsParamsDetail")
	public Result<List<SpGoodsParamsDetail>> list(Page page, SpGoodsParamsDetail spGoodsParamsDetail) {
		page.start();
		List<SpGoodsParamsDetail> list = spGoodsParamsDetailService.find(spGoodsParamsDetail);

		return new Result<>(list).total(new PageInfo<SpGoodsParamsDetail>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsParamsDetail/{id}")
	public Result<SpGoodsParamsDetail> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsParamsDetailService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsParamsDetail")
	public Result<?> save(@Valid @RequestBody SpGoodsParamsDetail spGoodsParamsDetail) {
		spGoodsParamsDetail.setId(IdMaker.get());
		spGoodsParamsDetailService.save(spGoodsParamsDetail);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsParamsDetail/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsParamsDetail spGoodsParamsDetail) {
		spGoodsParamsDetail.setId(id);
		spGoodsParamsDetailService.updateByIdSelective(spGoodsParamsDetail);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsParamsDetail/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsParamsDetailService.removeById(id);

		return Result.SUCCESS;
	}
}
