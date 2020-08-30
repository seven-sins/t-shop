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
import com.hiya3d.admin.sp.comment.service.SpGoodsCommentService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.sp.comment.SpGoodsComment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 评论
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "评论")
@RestController
public class SpGoodsCommentController {

	@Autowired
	SpGoodsCommentService spGoodsCommentService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsComment")
	public Result<List<SpGoodsComment>> list(Page page, SpGoodsComment spGoodsComment) {
		page.start();
		List<SpGoodsComment> list = spGoodsCommentService.find(spGoodsComment);

		return new Result<>(list).total(new PageInfo<SpGoodsComment>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsComment/{id}")
	public Result<SpGoodsComment> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsCommentService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsComment")
	public Result<?> save(@Valid @RequestBody SpGoodsComment spGoodsComment) {
		spGoodsComment.setId(IdMaker.get());
		spGoodsCommentService.save(spGoodsComment);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsComment/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsComment spGoodsComment) {
		spGoodsComment.setId(id);
		spGoodsCommentService.updateByIdSelective(spGoodsComment);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsComment/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsCommentService.removeById(id);

		return Result.SUCCESS;
	}
}
