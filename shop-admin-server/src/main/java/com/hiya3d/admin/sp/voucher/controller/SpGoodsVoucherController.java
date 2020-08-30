package com.hiya3d.admin.sp.voucher.controller;

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
import com.hiya3d.admin.sp.voucher.service.SpGoodsVoucherService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.voucher.SpGoodsVoucher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品可用优惠券
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品可用优惠券")
@RestController
public class SpGoodsVoucherController {

	@Autowired
	SpGoodsVoucherService spGoodsVoucherService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsVoucher")
	public Result<List<SpGoodsVoucher>> list(Page page, SpGoodsVoucher spGoodsVoucher) {
		page.start();
		List<SpGoodsVoucher> list = spGoodsVoucherService.find(spGoodsVoucher);

		return new Result<>(list).total(new PageInfo<SpGoodsVoucher>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsVoucher/{id}")
	public Result<SpGoodsVoucher> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsVoucherService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsVoucher")
	public Result<?> save(@Valid @RequestBody SpGoodsVoucher spGoodsVoucher) {
		spGoodsVoucher.setId(IdMaker.get());
		spGoodsVoucherService.save(spGoodsVoucher);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsVoucher/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsVoucher spGoodsVoucher) {
		spGoodsVoucher.setId(id);
		spGoodsVoucherService.updateByIdSelective(spGoodsVoucher);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsVoucher/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsVoucherService.removeById(id);

		return Result.SUCCESS;
	}
}
