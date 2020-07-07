package com.chenxt.bootdemo.base.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 *
 * @author chenxt
 * @date 2020/06/23
 */
public class PinyinUtils {

    /**
     * 获取拼音
     *
     * @param value 原内容
     * @return 拼音
     */
    public static String toPinYin(String value) {
        if (value == null) {
            return null;
        }
        HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
        pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            return PinyinHelper.toHanYuPinyinString(value, pyFormat, "", true);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            return "";
        }
    }
}
