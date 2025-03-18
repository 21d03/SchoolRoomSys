package com.dl.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author dongliang
 * @date 2024/11/01 23:21:21
 * @description
 **/
public class SpellUtil {

    private static final HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    static {
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public static String toPinyin(String input) {
        StringBuilder pinyinBuilder = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyinArray != null) {
                        pinyinBuilder.append(pinyinArray[0]);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinBuilder.append(c);
            }
        }
        return pinyinBuilder.toString();
    }

}
