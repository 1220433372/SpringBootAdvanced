package com.sunxs.boot.framework.validators;

/**
 * @author: 孙先生
 * @createTime: 2023/06/06 22:26
 * @description:
 */


import com.sunxs.boot.framework.annotation.EnumValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 校验器
 */
public class EnumValuesConstraintValidator implements ConstraintValidator<EnumValues, Integer> {
    /**
     * 存储枚举的值
     */
    private Set<Integer> ints = new HashSet<>();

    /**
     * 初始化方法
     *
     * @param enumValues 校验的注解
     */
    @Override
    public void initialize(EnumValues enumValues) {
        for (int value : enumValues.values()) {
            ints.add(value);
        }
    }

    /**
     * @param value   入参传的值
     * @param context
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //判断是否包含这个值
        return ints.contains(value);
    }
}
