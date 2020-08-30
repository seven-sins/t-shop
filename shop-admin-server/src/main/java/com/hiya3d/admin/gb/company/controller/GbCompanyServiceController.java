package com.hiya3d.admin.gb.company.controller;

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
import com.hiya3d.admin.gb.company.service.GbCompanyServiceService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.company.GbCompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 公司
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "公司客服")
@RestController
public class GbCompanyServiceController {

	@Autowired
	GbCompanyServiceService gbCompanyServiceService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbCompanyService")
	public Result<List<GbCompanyService>> list(Page page, GbCompanyService gbCompanyService) {
		page.start();
		List<GbCompanyService> list = gbCompanyServiceService.find(gbCompanyService);

		return new Result<>(list).total(new PageInfo<GbCompanyService>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbCompanyService/{id}")
	public Result<GbCompanyService> getById(@PathVariable("id") String id) {
		return new Result<>(gbCompanyServiceService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbCompanyService")
	public Result<?> save(@Valid @RequestBody GbCompanyService gbCompanyService) {
		gbCompanyService.setId(IdMaker.get());
		gbCompanyServiceService.save(gbCompanyService);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbCompanyService/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbCompanyService gbCompanyService) {
		gbCompanyService.setId(id);
		gbCompanyServiceService.updateByIdSelective(gbCompanyService);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbCompanyService/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbCompanyServiceService.removeById(id);

		return Result.SUCCESS;
	}
}
