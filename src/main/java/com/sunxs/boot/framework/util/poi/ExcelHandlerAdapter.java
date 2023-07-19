package com.sunxs.boot.framework.util.poi;

/**
 * @Author 孙先辉
 * @Date 2023/7/10
 */
public interface ExcelHandlerAdapter {
    /**
     * 格式化
     *
     * @param value 单元格数据值
     * @param args excel注解args参数组
     *
     * @return 处理后的值
     */
    Object format(Object value, String[] args);
}
