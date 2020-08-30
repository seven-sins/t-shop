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
import com.hiya3d.admin.gb.company.service.GbCompanyLogisticsService;
import com.hiya3d.base.request.Page;
import com.hiya3d.base.response.Result;
import com.hiya3d.base.utils.IdMaker;
import com.hiya3d.model.gb.company.GbCompanyLogistics;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 公司物流
 * 
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "公司物流")
@RestController
public class GbCompanyLogisticsController {

	@Autowired
	GbCompanyLogisticsService gbCompanyLogisticsService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/gbCompanyLogistics")
	public Result<List<GbCompanyLogistics>> list(Page page, GbCompanyLogistics gbCompanyLogistics) {
		page.start();
		List<GbCompanyLogistics> list = gbCompanyLogisticsService.find(gbCompanyLogistics);

		return new Result<>(list).total(new PageInfo<GbCompanyLogistics>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/gbCompanyLogistics/{id}")
	public Result<GbCompanyLogistics> getById(@PathVariable("id") String id) {
		return new Result<>(gbCompanyLogisticsService.getById(id));
	}

	@ApiOperation(value = "保存")
	@PostMapping("/gbCompanyLogistics")
	public Result<?> save(@Valid @RequestBody GbCompanyLogistics gbCompanyLogistics) {
		gbCompanyLogistics.setId(IdMaker.get());
		gbCompanyLogisticsService.save(gbCompanyLogistics);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "修改")
	@PutMapping("/gbCompanyLogistics/{id}")
	public Result<?> update(@PathVariable("id") String id, @Valid @RequestBody GbCompanyLogistics gbCompanyLogistics) {
		gbCompanyLogistics.setId(id);
		gbCompanyLogisticsService.updateByIdSelective(gbCompanyLogistics);

		return Result.SUCCESS;
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("/gbCompanyLogistics/{id}")
	public Result<?> removeById(@PathVariable("id") String id) {
		gbCompanyLogisticsService.removeById(id);

		return Result.SUCCESS;
	}
}
