package com.hiya3d.base.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义INT验证注解
 * 
 * @author Rex.Tan
 * @date 2019年7月29日 下午2:42:29
 */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntValueValidator.class)
public @interface IntValue {

	String message() default "整型超出取值超出范围";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}