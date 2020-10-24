package com.hiya3d.admin.gb.goods.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hiya3d.admin.gb.goods.service.GbCategoryService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.common.utils.Assert;
import com.hiya3d.model.gb.goods.GbCategory;
import com.hiya3d.model.gb.goods.vo.GbCategoryVo;

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
public class GbCategoryController {
	@Autowired
	GbCategoryService gbCategoryService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbCategory")
	public Result<List<GbCategoryVo>> list(Page page, GbCategoryVo gbCategoryVo) {
		List<GbCategoryVo> list = gbCategoryService.find(gbCategoryVo);
		List<GbCategoryVo> treeList = new ArrayList<>();
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
	private void refactory(List<GbCategoryVo> list, List<GbCategoryVo> treeList){
		if(list == null || list.isEmpty()) {
			return;
		}
		for(GbCategoryVo item: list) {
			if(StringUtils.isBlank(item.getParentId())) {
				item.setText(item.getCategoryName());
				item.setParentId(StringUtils.isBlank(item.getParentId()) ? null : item.getParentId());
				item.setChildren(this.getChildren(list, item.getId()));
				treeList.add(item);
			}
		}
	}
	
	private List<GbCategoryVo> getChildren(List<GbCategoryVo> list, String parentId){
		List<GbCategoryVo> children = new ArrayList<>();
		for(GbCategoryVo item: list) {
			if(parentId.equals(item.getParentId())) {
				item.setText(item.getCategoryName());
				item.setParentId(StringUtils.isBlank(item.getParentId()) ? null : item.getParentId());
				item.setChildren(this.getChildren(list, item.getId()));
				children.add(item);
			}
		}
		
		return children.isEmpty() ? null : children;
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbCategory/{id}")
	public Result<GbCategory> getById(@PathVariable("id") String id) {
		return new Result<>(gbCategoryService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbCategory")
	public Result<?> save(@Valid @RequestBody GbCategory gbCategory) {
		// 检查分类名称是否已存在
		this.checkExist(gbCategory.getCategoryName(), null);
		if(StringUtils.isBlank(gbCategory.getParentId())) {
			gbCategory.setParentId("");
			Assert.notEmpty(gbCategory.getThemeId(), "主题不能为空");
		} else {
			gbCategory.setThemeId(this.getThemeIdByParent(gbCategory.getParentId()));
		}
		gbCategory.setId(IdMaker.get());
		gbCategoryService.save(gbCategory);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbCategory/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbCategory gbCategory) {
		this.checkLogic(id, gbCategory.getParentId());
		// 检查分类名称是否已存在
		this.checkExist(gbCategory.getCategoryName(), id);
		if(StringUtils.isBlank(gbCategory.getParentId())) {
			gbCategory.setParentId("");
			Assert.notEmpty(gbCategory.getThemeId(), "主题不能为空");
		} else {
			gbCategory.setThemeId(this.getThemeIdByParent(gbCategory.getParentId()));
		}
		gbCategory.setId(id);
		gbCategoryService.updateByIdSelective(gbCategory);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbCategory/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbCategoryService.removeById(id);

		return Result.SUCCESS;
	}
	
	private String getThemeIdByParent(String parentId) {
		if(StringUtils.isBlank(parentId)) {
			return "";
		}
		GbCategory parent = gbCategoryService.getById(parentId);
		Assert.notNull(parent, "参数错误, 上级分类不存在");
		
		return parent.getThemeId();
	}
	
	/**
	 * 重复名称检查
	 * @author Rex.Tan
	 * @date 2020-10-20 11:32:21
	 * @param categoryName
	 * @param id
	 */
	private void checkExist(String categoryName, String id) {
		GbCategory dbData = gbCategoryService.getByCategoryName(categoryName);
		if(dbData == null) {
			return;
		}
		Assert.isTrue(dbData.getId().equals(id), "分类名称已存在");
	}
	
	/**
	 * 检查数据逻辑, 上级节点不能选择当前节点以及下级节点
	 * @author Rex.Tan
	 * @date 2020-10-20 13:44:38
	 * @param id
	 * @param parentId
	 */
	private void checkLogic(String id, String parentId) {
		if(StringUtils.isBlank(parentId)) {
			return;
		}
		Assert.isTrue(!id.equals(parentId), "上级分类不能选择当前节点");
		List<GbCategoryVo> list = gbCategoryService.find(new GbCategoryVo());
		List<GbCategoryVo> treeList = new ArrayList<>();
		this.refactory(list, treeList);
		
		this.checkLogic(id, parentId, treeList);
	}
	
	private void checkLogic(String id, String parentId, List<GbCategoryVo> list) {
		for(GbCategoryVo item: list) {
			if(id.equals(item.getId())) {
				checkLogic(parentId, item.getChildren());
			}
		}
	}
	
	private void checkLogic(String parentId, List<GbCategoryVo> list) {
		if(list == null) {
			return;
		}
		for(GbCategoryVo item: list) {
			Assert.isTrue(!parentId.equals(item.getId()), "上级节点不能选择下级节点");
			checkLogic(parentId, item.getChildren());
		}
	}
	
}
