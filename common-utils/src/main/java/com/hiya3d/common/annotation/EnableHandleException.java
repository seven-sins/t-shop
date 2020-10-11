package com.hiya3d.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.hiya3d.common.conf.exception.CustomExceptionHandler;

/**
 * 引入数据库配置
 * @author Rex.Tan
 * 2019年1月3日 上午11:47:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Inherited
@Import({ EnableHandleException.HandleExceptionSelector.class })
public @interface EnableHandleException {

	static class HandleExceptionSelector implements ImportSelector {

		@Override
		public String[] selectImports(AnnotationMetadata importingClassMetadata) {
			return new String[] { CustomExceptionHandler.class.getName() };
		}
	}
}