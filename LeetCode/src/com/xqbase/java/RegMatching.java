package com.xqbase.java;

/**
 * LeetCodeTen -- Regular Expression Matching.
 *
 * @author Tony He
 */
public class RegMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch("aaa","aa"));
    }

    private static boolean isMatch(String s, String p) {

        if (p.length() == 0) {
            return s.length() == 0;
        }

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.length() < 1 || (p.charAt(0) != s.charAt(0) && p.charAt(0) != '.')) {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        } else {
            int len = s.length();

            int i = -1;
            while (i < len && (i < 0 || p.charAt(0) == '.' || s.charAt(i) == p.charAt(0))) {
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i ++;
            }

            return false;
        }
    }
}
