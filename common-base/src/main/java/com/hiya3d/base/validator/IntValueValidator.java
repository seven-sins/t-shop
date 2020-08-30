package com.hiya3d.base.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义INT验证器
 * 
 * @author Rex.Tan
 * @date 2019年7月29日 下午2:42:43
 */
public class IntValueValidator implements ConstraintValidator<IntValue, Integer> {

	@Override
	public void initialize(IntValue intValue) {
		
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext validatorContext) {
		if (value == null) {
			return true;
		}
		return value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE;
	}
}
