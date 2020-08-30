package com.hiya3d.common.conf.mybatis;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

/**
 * druid配置
 * @author Rex.Tan
 * @date 2018年12月25日 上午9:51:26
 */
@Configuration
public class DruidConfiguration {
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource druidDataSource() {
		return new DruidDataSource();
	}

	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(druidDataSource());
    }
	
	@Bean
	public ServletRegistrationBean<?> statViewServle() {
		ServletRegistrationBean<?> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
		// 白名单：
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow)
		servletRegistrationBean.addInitParameter("deny", "192.168.0.77");
		// 登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "123456");

		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");

		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<?> statFilter() {
		FilterRegistrationBean<?> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");

		// 添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

		return filterRegistrationBean;
	}

	@Bean
	public StatFilter statFilter11() {
		StatFilter statFilter = new StatFilter();
		statFilter.setLogSlowSql(true);
		statFilter.setMergeSql(true);
		statFilter.setSlowSqlMillis(1000);

		return statFilter;
	}

	@Bean
	public WallFilter wallFilter() {
		WallFilter wallFilter = new WallFilter();
		// 允许执行多条SQL
		WallConfig config = new WallConfig();
		config.setMultiStatementAllow(true);
		wallFilter.setConfig(config);

		return wallFilter;
	}
}
