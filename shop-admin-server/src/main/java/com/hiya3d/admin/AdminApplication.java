package com.hiya3d.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.hiya3d.common.annotation.EnableDatabase;
import com.hiya3d.common.annotation.EnableHandleException;
import com.hiya3d.common.annotation.EnableLogAop;
import com.hiya3d.common.annotation.EnableSpringUtil;
import com.hiya3d.common.annotation.EnableSwagger;
import com.hiya3d.common.annotation.EnableWebMvcConfig;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author rex.tan
 * @date 2019年11月19日 下午4:21:02
 */
@EnableDatabase
@EnableSpringUtil
@EnableSwagger
@EnableLogAop
@EnableWebMvcConfig
@EnableAsync
@EnableHandleException
@SpringBootApplication
@EnableScheduling
@MapperScan({ "com.hiya3d.mapper" })
public class AdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Bean("restTemplate")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
