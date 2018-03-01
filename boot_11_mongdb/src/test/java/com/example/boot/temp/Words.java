package com.example.boot.temp;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/2
 * 统计文本或者网页中单词的频率
 */
public class Words {

    private static Set<String> NON_WORDS = new HashSet<String>() {
        {
            add("the");add("and");add("of");add("to");add("a");
            add("i");add("it");add("in");add("or");add("is");add("d");
            add("s");add("as");add("so");add("but");add("be");
        }
    };

    public static Map wordFreq1(String words) {
        TreeMap<String, Integer> wordMap = new TreeMap<>();
        Matcher matcher = Pattern.compile("\\w").matcher(words);
        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            if (!NON_WORDS.contains(word)) {
                if (wordMap.get(word) == null) {
                    wordMap.put(word, 1);
                } else {
                    wordMap.put(word, wordMap.get(word) + 1);
                }
            }
        }
        return wordMap;
    }
}
