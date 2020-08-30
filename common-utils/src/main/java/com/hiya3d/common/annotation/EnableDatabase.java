package com.hiya3d.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.hiya3d.common.conf.mybatis.CustomInterceptorAutoConfig;
import com.hiya3d.common.conf.mybatis.DruidConfiguration;
import com.hiya3d.common.conf.mybatis.MyBatisConfig;

/**
 * 引入数据库配置
 * @author rex.tan
 * @date 2019年11月19日 下午4:43:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Inherited
@Import({ EnableDatabase.DatabaseImportSelector.class })
public @interface EnableDatabase {

	static class DatabaseImportSelector implements ImportSelector {

		@Override
		public String[] selectImports(AnnotationMetadata importingClassMetadata) {
			return new String[] { CustomInterceptorAutoConfig.class.getName(), DruidConfiguration.class.getName(), MyBatisConfig.class.getName() };
		}
	}
}