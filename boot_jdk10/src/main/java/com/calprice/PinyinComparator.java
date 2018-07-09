package com.calprice;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/19
 */
public class PinyinComparator<T> implements Comparator<T> {

    public int compare(T o1, T o2) {
        String key1 = o1.toString();
        String key2 = o2.toString();
        for (int i = 0; i < key1.length() && i < key2.length(); i++) {
            int codePoint1 = key1.charAt(i);
            int codePoint2 = key2.charAt(i);
            if (Character.isSupplementaryCodePoint(codePoint1)
                    || Character.isSupplementaryCodePoint(codePoint2)) {
                i++;
            }
            if (codePoint1 != codePoint2) {
                if (Character.isSupplementaryCodePoint(codePoint1)
                        || Character.isSupplementaryCodePoint(codePoint2)) {
                    return codePoint1 - codePoint2;
                }
                String pinyin1 = pinyin((char) codePoint1);
                String pinyin2 = pinyin((char) codePoint2);
                if (pinyin1 != null && pinyin2 != null) { // 两个字符都是汉字
                    if (!pinyin1.equals(pinyin2)) {
                        return pinyin1.compareTo(pinyin2);
                    }
                } else {
                    return codePoint1 - codePoint2;
                }
            }
        }
        return key1.length() - key2.length();
    }

    private String pinyin(char c) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyins == null) {
            return null;   //如果转换结果为空，则返回null
        }
        return pinyins[0];   //如果为多音字返回第一个音节
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Adisen");
        list.add("adisen");
        list.add("adisem");
        list.add("阿姨");
        list.add("Adisem");
        list.add("bulsi");
        list.add("Kobe");
        list.add("布丁");
        list.add("杜甫");
        list.add("元方");
        list.add("松江3");
        list.add("松江");
        list.add("松江2");
        list.add("刘啊送");
        list.add("刘备");
        list.add("刘啊啊");

        list.sort(new PinyinComparator<String>());
        System.out.println(list);
    }
}
