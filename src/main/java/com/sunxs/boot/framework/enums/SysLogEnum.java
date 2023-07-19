package com.sunxs.boot.framework.enums;

/**
 * @Author 孙先辉
 * @Date 2023/7/10
 */
public enum SysLogEnum {

    /**
     * 其它
     **/
    OTHER(0, "其它"),
    /**
     * 新增
     **/
    ADD(1, "新增"),
    /**
     * 修改
     **/
    UPDATE(2, "修改"),
    /**
     * 删除
     **/
    DELETE(3, "删除"),
    /**
     * 详情查询
     **/
    INFO(4, "详情查询"),
    /**
     * 所有列表
     **/
    ALL_LIST(5, "所有列表"),
    /**
     * 分页列表
     **/
    LIST(6, "分页列表"),
    /**
     * 其它查询
     **/
    OTHER_QUERY(7, "其它查询"),
    /**
     * 上传文件
     **/
    UPLOAD(8, "上传文件"),
    /**
     * 导出
     */
    EXPORT(9,"导出"),
    /**
     * 导入
     */
    IMPORT(10,"导入");

    private Integer code;
    private String desc;

    SysLogEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SysLogEnum getSysLogEnum(Integer code) {
        if (code == null) {
            return OTHER;
        }
        for (SysLogEnum sysLogEnum : values()) {
            if (sysLogEnum.getCode().equals(code)) {
                return sysLogEnum;
            }
        }
        return OTHER;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
