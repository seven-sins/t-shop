package com.hiya3d.base.validator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author rex.tan
 * @date 2019年11月22日 下午2:24:20
 */
public class AmountValidator implements ConstraintValidator<Amount, BigDecimal> {
    /**
     * 表示金额的正则表达式
     */
    private static String REGEX = "^(\\-{0,1})\\d{1,11}(\\.\\d{1,4})?$";
    private static Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public void initialize(Amount amount) {

    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext validatorContext) {
    	if(value == null) {
    		return true;
    	}
        return PATTERN.matcher(value.toString()).matches();
    }

}
