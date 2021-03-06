package com.hiya3d.admin.gb.controller;

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
import com.hiya3d.admin.gb.service.GbCompanyService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.company.GbCompany;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 公司
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "公司")
@RestController
public class GbCompanyController {

	@Autowired
	GbCompanyService gbCompanyService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbCompany")
	public Result<List<GbCompany>> list(Page page, GbCompany gbCompany) {
		page.start();
		List<GbCompany> list = gbCompanyService.find(gbCompany);

		return new Result<>(list).total(new PageInfo<GbCompany>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbCompany/{id}")
	public Result<GbCompany> getById(@PathVariable("id") String id) {
		return new Result<>(gbCompanyService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbCompany")
	public Result<?> save(@Valid @RequestBody GbCompany gbCompany) {
		gbCompany.setId(IdMaker.get());
		gbCompanyService.save(gbCompany);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbCompany/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbCompany gbCompany) {
		gbCompany.setId(id);
		gbCompanyService.updateByIdSelective(gbCompany);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbCompany/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbCompanyService.removeById(id);

		return Result.SUCCESS;
	}
}
