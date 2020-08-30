package com.hiya3d.base.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义金额验证注解
 * @author Tan Ling
 * @date 2019年1月7日 下午2:11:26
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=AmountValidator.class)
public @interface Amount {

    String message() default "金额超出取值范围{11,4}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}