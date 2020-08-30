package com.hiya3d.admin.sp.comment.controller;

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
import com.hiya3d.admin.sp.comment.service.SpGoodsCommentImgService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.comment.SpGoodsCommentImg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 评论图片
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "评论图片")
@RestController
public class SpGoodsCommentImgController {

	@Autowired
	SpGoodsCommentImgService spGoodsCommentImgService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsCommentImg")
	public Result<List<SpGoodsCommentImg>> list(Page page, SpGoodsCommentImg spGoodsCommentImg) {
		page.start();
		List<SpGoodsCommentImg> list = spGoodsCommentImgService.find(spGoodsCommentImg);

		return new Result<>(list).total(new PageInfo<SpGoodsCommentImg>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsCommentImg/{id}")
	public Result<SpGoodsCommentImg> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsCommentImgService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsCommentImg")
	public Result<?> save(@Valid @RequestBody SpGoodsCommentImg spGoodsCommentImg) {
		spGoodsCommentImg.setId(IdMaker.get());
		spGoodsCommentImgService.save(spGoodsCommentImg);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsCommentImg/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsCommentImg spGoodsCommentImg) {
		spGoodsCommentImg.setId(id);
		spGoodsCommentImgService.updateByIdSelective(spGoodsCommentImg);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsCommentImg/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsCommentImgService.removeById(id);

		return Result.SUCCESS;
	}
}
