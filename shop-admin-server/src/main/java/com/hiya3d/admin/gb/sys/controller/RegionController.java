package com.hiya3d.admin.gb.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hiya3d.admin.gb.sys.service.RegionService;
import com.hiya3d.base.response.Result;
import com.hiya3d.model.gb.sys.Region;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 城市
 * @author Rex.Tan
 * @date 2020年8月30日 下午1:20:01
 */
@Api(tags = "城市")
@RestController
public class RegionController {

	@Autowired
	RegionService regionService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/region")
	public Result<List<Region>> list(Region region) {
		List<Region> list = regionService.find(region);
		return new Result<>(list).total(new PageInfo<Region>(list).getTotal());
	}

	@ApiOperation(value = "单记录查询")
	@GetMapping("/region/{id}")
	public Result<Region> getById(@PathVariable("id") String id) {
		return new Result<>(regionService.getById(id));
	}
}
