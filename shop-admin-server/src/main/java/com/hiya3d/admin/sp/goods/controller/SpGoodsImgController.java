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
import com.hiya3d.admin.sp.goods.service.SpGoodsImgService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.goods.SpGoodsImg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品图片
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品图片")
@RestController
public class SpGoodsImgController {

	@Autowired
	SpGoodsImgService spGoodsImgService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsImg")
	public Result<List<SpGoodsImg>> list(Page page, SpGoodsImg spGoodsImg) {
		page.start();
		List<SpGoodsImg> list = spGoodsImgService.find(spGoodsImg);

		return new Result<>(list).total(new PageInfo<SpGoodsImg>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsImg/{id}")
	public Result<SpGoodsImg> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsImgService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsImg")
	public Result<?> save(@Valid @RequestBody SpGoodsImg spGoodsImg) {
		spGoodsImg.setId(IdMaker.get());
		spGoodsImgService.save(spGoodsImg);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsImg/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsImg spGoodsImg) {
		spGoodsImg.setId(id);
		spGoodsImgService.updateByIdSelective(spGoodsImg);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsImg/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsImgService.removeById(id);

		return Result.SUCCESS;
	}
}
