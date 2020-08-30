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
import com.hiya3d.admin.sp.voucher.service.SpVoucherService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.voucher.SpVoucher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 优惠券
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "优惠券")
@RestController
public class SpVoucherController {

	@Autowired
	SpVoucherService spVoucherService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spVoucher")
	public Result<List<SpVoucher>> list(Page page, SpVoucher spVoucher) {
		page.start();
		List<SpVoucher> list = spVoucherService.find(spVoucher);

		return new Result<>(list).total(new PageInfo<SpVoucher>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spVoucher/{id}")
	public Result<SpVoucher> getById(@PathVariable("id") String id) {
		return new Result<>(spVoucherService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spVoucher")
	public Result<?> save(@Valid @RequestBody SpVoucher spVoucher) {
		spVoucher.setId(IdMaker.get());
		spVoucherService.save(spVoucher);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spVoucher/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpVoucher spVoucher) {
		spVoucher.setId(id);
		spVoucherService.updateByIdSelective(spVoucher);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spVoucher/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spVoucherService.removeById(id);

		return Result.SUCCESS;
	}
}
