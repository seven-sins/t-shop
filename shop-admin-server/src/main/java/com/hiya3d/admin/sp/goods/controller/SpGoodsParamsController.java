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
import com.hiya3d.admin.sp.goods.service.SpGoodsParamsService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.goods.SpGoodsParams;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品参数
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品参数")
@RestController
public class SpGoodsParamsController {

	@Autowired
	SpGoodsParamsService spGoodsParamsService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsParams")
	public Result<List<SpGoodsParams>> list(Page page, SpGoodsParams spGoodsParams) {
		page.start();
		List<SpGoodsParams> list = spGoodsParamsService.find(spGoodsParams);

		return new Result<>(list).total(new PageInfo<SpGoodsParams>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsParams/{id}")
	public Result<SpGoodsParams> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsParamsService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsParams")
	public Result<?> save(@Valid @RequestBody SpGoodsParams spGoodsParams) {
		spGoodsParams.setId(IdMaker.get());
		spGoodsParamsService.save(spGoodsParams);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsParams/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsParams spGoodsParams) {
		spGoodsParams.setId(id);
		spGoodsParamsService.updateByIdSelective(spGoodsParams);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsParams/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsParamsService.removeById(id);

		return Result.SUCCESS;
	}
}
