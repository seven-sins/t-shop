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
import com.hiya3d.admin.sp.voucher.service.SpVoucherReceiveService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.voucher.SpVoucherReceive;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 领取优惠券
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "领取优惠券")
@RestController
public class SpVoucherReceiveController {

	@Autowired
	SpVoucherReceiveService spVoucherReceiveService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spVoucherReceive")
	public Result<List<SpVoucherReceive>> list(Page page, SpVoucherReceive spVoucherReceive) {
		page.start();
		List<SpVoucherReceive> list = spVoucherReceiveService.find(spVoucherReceive);

		return new Result<>(list).total(new PageInfo<SpVoucherReceive>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spVoucherReceive/{id}")
	public Result<SpVoucherReceive> getById(@PathVariable("id") String id) {
		return new Result<>(spVoucherReceiveService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spVoucherReceive")
	public Result<?> save(@Valid @RequestBody SpVoucherReceive spVoucherReceive) {
		spVoucherReceive.setId(IdMaker.get());
		spVoucherReceiveService.save(spVoucherReceive);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spVoucherReceive/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpVoucherReceive spVoucherReceive) {
		spVoucherReceive.setId(id);
		spVoucherReceiveService.updateByIdSelective(spVoucherReceive);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spVoucherReceive/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spVoucherReceiveService.removeById(id);

		return Result.SUCCESS;
	}
}
