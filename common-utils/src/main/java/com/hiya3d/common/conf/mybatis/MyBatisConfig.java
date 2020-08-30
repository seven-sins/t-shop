package com.hiya3d.common.conf.mybatis;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.github.pagehelper.PageInterceptor;

/**
 * mybatis配置
 * @author Rex.Tan
 * @date 2018年12月25日 上午9:51:06
 */
@Configuration
@AutoConfigureAfter({ DruidConfiguration.class })
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {
	static final Logger LOG = LoggerFactory.getLogger(MyBatisConfig.class);
	@Autowired
	private DataSource dataSource;
	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;
	/**
	 * 构建MyBatis入口类
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		 SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		String[] locations = mapperLocations.split(",");
		Resource[] resource = new Resource[0];
		for (String item : locations) {
			Resource[] res = pathMatchingResourcePatternResolver.getResources(item);
			resource = ArrayUtils.addAll(resource, res);
		}

		bean.setMapperLocations(resource);
		bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		bean.getObject().getConfiguration().setAutoMappingBehavior(AutoMappingBehavior.FULL);

		// 分页插件
		PageInterceptor pageHelper = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("reasonable", "false");
		properties.setProperty("authRuntimeDialect", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);

		// 添加插件
		bean.setPlugins(new Interceptor[] { pageHelper });

		// 添加XML目录 "classpath:mapper/**/*.xml"
		// ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// bean.setMapperLocations(resolver.getResources(mapperLocations));

		return bean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	/**
	 * 构建事务管理器
	 */
	@Override
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 事务配置
	 * 
	 * @param transactionManager
	 * @return
	 */
	@Bean
	public TransactionInterceptor transactionInterceptor() {
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		transactionInterceptor.setTransactionManager(annotationDrivenTransactionManager());
		Properties properties = new Properties();
		properties.setProperty("find*", "PROPAGATION_REQUIRED, readOnly");
		properties.setProperty("get*", "PROPAGATION_REQUIRED, readOnly");
		properties.setProperty("query*", "PROPAGATION_REQUIRED, readOnly");
		properties.setProperty("select*", "PROPAGATION_REQUIRED, readOnly");
		properties.setProperty("insert*", "PROPAGATION_REQUIRED");
		properties.setProperty("delete*", "PROPAGATION_REQUIRED");
		properties.setProperty("update*", "PROPAGATION_REQUIRED");
		properties.setProperty("add*", "PROPAGATION_REQUIRED");
		properties.setProperty("handle*", "PROPAGATION_REQUIRED");
		properties.setProperty("**", "PROPAGATION_REQUIRED");
		transactionInterceptor.setTransactionAttributes(properties);

		return transactionInterceptor;
	}

	@Bean
	public BeanNameAutoProxyCreator transactionAutoProxy() {
		BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
		transactionAutoProxy.setProxyTargetClass(false);
		transactionAutoProxy.setBeanNames(new String[] { "*ServiceImpl" });
		transactionAutoProxy.setInterceptorNames(new String[] { "transactionInterceptor" });

		return transactionAutoProxy;
	}
}
