package com.hiya3d.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiya3d.base.response.Result;

/**
 * 探针接口
 * @author rex.tan
 * @date 2019年11月13日 上午11:34:09
 */
@RestController
public class HealthCheck {

	@GetMapping("/health-check")
	public Result<?> healthCheck() {
		return Result.SUCCESS;
	}
}
