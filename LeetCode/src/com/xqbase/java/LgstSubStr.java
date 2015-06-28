package com.xqbase.java;

/**
 * LeetCode3 -- Longest Substring without Repeating character.
 *
 * @author Tony He
 */
public class LgstSubStr {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("abcdef"));
        System.out.println(lengthOfLongestSubstring("abcabcabcabcd"));
    }

    private static int lengthOfLongestSubstring(String s) {
        boolean[] flag = new boolean[256];
        int length = 0;
        int start = 0;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (flag[c]) {
                length = Math.max(length, i - start);
                for (int j = start; j < i; j++) {
                    if (chars[j] == c) {
                        start = j + 1;
                        break;
                    }
                    flag[chars[j]] = false;
                }
            } else {
                flag[c] = true;
            }
        }

        length = Math.max(chars.length - start, length);
        return length;
    }
}
