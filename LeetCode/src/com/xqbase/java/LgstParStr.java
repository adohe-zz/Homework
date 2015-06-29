package com.xqbase.java;

/**
 * LeetCodeFive -- find the longest palindromic substring in S.
 *
 * @author Tony He
 */
public class LgstParStr {


    public static void main(String[] args) {
        System.out.println(longestPalindrome("abc"));
        System.out.println(longestPalindrome("aaaaa"));
        System.out.println(longestPalindrome("abccba"));
        System.out.println(longestPalindrome("abba"));
    }

    public static String longestPalindrome(String s) {
        if (s == null)
            return null;

        if (s.length() == 1)
            return s;

        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String temp = center(s, i, i);
            if (temp.length() > longest.length())
                longest = temp;

            temp = center(s, i, i + 1);
            if (temp.length() > longest.length())
                longest = temp;
        }

        return longest;
    }

    private static String center(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin --;
            end ++;
        }

        return s.substring(begin + 1, end);
    }
}
