package com.hiya3d.admin.sp.category.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hiya3d.admin.sp.category.service.SpGoodsCategoryService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.common.conf.user.context.UserContext;
import com.hiya3d.model.sp.category.SpGoodsCategory;
import com.hiya3d.model.sp.category.vo.SpGoodsCategoryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品分类
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "商品分类")
@RestController
public class SpGoodsCategoryController {
	@Autowired
	SpGoodsCategoryService spGoodsCategoryService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/spGoodsCategory")
	public Result<List<SpGoodsCategoryVo>> list(Page page, SpGoodsCategory spGoodsCategory) {
		spGoodsCategory.setShopId(UserContext.get().getShopId());
		List<SpGoodsCategory> list = spGoodsCategoryService.find(spGoodsCategory);
		List<SpGoodsCategoryVo> treeList = new ArrayList<>();
		this.refactory(list, treeList);
		
		return new Result<>(treeList);
	}
	
	/**
	 * 将列表转为树形结构
	 * @author Rex.Tan
	 * @date 2020-10-19 17:06:34
	 * @param list
	 * @param treeList
	 */
	private void refactory(List<SpGoodsCategory> list, List<SpGoodsCategoryVo> treeList){
		if(list == null || list.isEmpty()) {
			return;
		}
		for(SpGoodsCategory item: list) {
			if(StringUtils.isBlank(item.getParentId())) {
				SpGoodsCategoryVo categoryVo = new SpGoodsCategoryVo();
				BeanUtils.copyProperties(item, categoryVo);
				categoryVo.setText(categoryVo.getCategoryName());
				categoryVo.setChildren(this.getChildren(list, categoryVo.getId()));
				treeList.add(categoryVo);
			}
		}
	}
	
	private List<SpGoodsCategoryVo> getChildren(List<SpGoodsCategory> list, String parentId){
		List<SpGoodsCategoryVo> children = new ArrayList<>();
		for(SpGoodsCategory item: list) {
			if(parentId.equals(item.getParentId())) {
				SpGoodsCategoryVo categoryVo = new SpGoodsCategoryVo();
				BeanUtils.copyProperties(item,  categoryVo);
				categoryVo.setText(categoryVo.getCategoryName());
				categoryVo.setChildren(this.getChildren(list, categoryVo.getId()));
				children.add(categoryVo);
			}
		}
		
		return children.isEmpty() ? null : children;
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/spGoodsCategory/{id}")
	public Result<SpGoodsCategory> getById(@PathVariable("id") String id) {
		return new Result<>(spGoodsCategoryService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/spGoodsCategory")
	public Result<?> save(@Valid @RequestBody SpGoodsCategory spGoodsCategory) {
		spGoodsCategory.setId(IdMaker.get());
		spGoodsCategory.setShopId(UserContext.get().getShopId());
		spGoodsCategoryService.save(spGoodsCategory);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/spGoodsCategory/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody SpGoodsCategory spGoodsCategory) {
		spGoodsCategory.setId(id);
		spGoodsCategoryService.updateByIdSelective(spGoodsCategory);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/spGoodsCategory/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		spGoodsCategoryService.removeById(id);

		return Result.SUCCESS;
	}
}
