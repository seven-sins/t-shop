package com.hiya3d.common.conf.mybatis;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;

/**
 * 修复因PageInterceptor导致不生效
 * @author rex.tan
 * @date 2018年12月9日 下午5:58:06
 */
@Configuration
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
public class CustomInterceptorAutoConfig {

	@Autowired
	private List<SqlSessionFactory> sqlSessionFactoryList;

	@PostConstruct
	public void addMyInterceptor() {
		CustomMybatisInterceptor interceptor = new CustomMybatisInterceptor();
		for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
			sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
		}
	}
}
