package com.example.boot_webflux.service;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/02
 */
public class Num2Rmb {

    private String[] hanArr = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private String[] unitArr = {"十", "百", "千"};

    /**
     * 将一个浮点数分解成整数部分和小数部分字符串
     *
     * @param num
     * @return
     */
    private String[] divide(double num) {
        // 强制转换，则得到整数部分
        long zheng = (long) num;
        long xiao = Math.round((num - zheng) * 100);
        return new String[]{zheng + "", String.valueOf(xiao)};
    }

    private String toHanStr(String numStr) {
        return null;
    }
}
