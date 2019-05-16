package com.example.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/29
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class Question3 {
    public static void main(String[] args) {
        String string = "abcabcbb";
        int n = string.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(string.charAt(j))) {
                set.add(string.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(string.charAt(i++));
            }
        }
        System.out.println(ans);
        solution2(string);
    }

    public static void solution2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        System.out.println(ans);
    }

    static void solution3(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        /*for (int i = 0; i < ; i++) {
            
        }*/
    }
}
