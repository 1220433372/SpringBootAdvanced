package com.sunxs.boot.framework.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author 孙先辉
 * @Date 2023/7/10
 */
@Getter
public enum GenderEnum {

    MAN(0, "男"),
    FEMALE(1, "女");

    GenderEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }
    // 系统的值 0
    @EnumValue
    private final int code;
    // 返回前端的值 男
    @JsonValue
    private final String descp;

}