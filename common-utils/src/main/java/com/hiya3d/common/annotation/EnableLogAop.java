package com.hiya3d.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.hiya3d.common.aop.RequestAop;

/**
 * 引入日志拦截输出AOP
 * @author rex.tan
 * @date 2019年11月19日 下午4:43:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Inherited
@Import({ EnableLogAop.LogAopSelector.class })
public @interface EnableLogAop {

	static class LogAopSelector implements ImportSelector {

		@Override
		public String[] selectImports(AnnotationMetadata importingClassMetadata) {
			return new String[] { RequestAop.class.getName() };
		}
	}
}