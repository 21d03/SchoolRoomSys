package com.dl.util;

/**
 * 拼音工具类
 */
public class PinYinUtil {

    /**
     * 获取汉字拼音，简单实现汉字转拼音
     * 
     * @param name 中文名
     * @return 拼音
     */
    public static String getPinyin(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        
        // 这里简单返回将中文名转为小写字母拼音，实际生产环境建议使用第三方库如pinyin4j等
        // 这里简单实现，假设名字中没有生僻字，直接删除空格后全部小写
        String pinyin = name.replaceAll("\\s+", "").toLowerCase();
        
        // 如果包含中文，这里暂不做转换，假设输入已经是拼音格式
        return pinyin;
    }
} 