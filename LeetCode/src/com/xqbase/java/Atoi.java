package com.xqbase.java;

/**
 * LeetCodeEight -- Convert String to Integer.
 *
 * @author Tony He
 */
public class Atoi {

    public static void main(String[] args) {
        System.out.println("  ".trim().isEmpty());
        System.out.println(myAtoi("473ni"));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(myAtoi("-2147483649"));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(myAtoi("2147483648"));
        System.out.println(myAtoi("-2147483647"));
        System.out.println(myAtoi("2147483646"));
        System.out.println(myAtoi("nihao"));
        System.out.println(myAtoi("+"));
    }

    private static int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }

        int result = 0;
        boolean negative = false;
        int i = 0, len = str.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;

        char firstChar = str.charAt(0);
        if (firstChar < '0') {
            if (firstChar == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
            } else if (firstChar != '+') {
                return 0;
            }

            if (len == 1) {
                return 0;
            }
            i ++;
        }
        multmin = limit / 10;
        while (i < len) {
            digit = Character.digit(str.charAt(i++), 10);
            if (digit < 0) {
                return negative ? result : -result;
            }
            if (result < multmin) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result *= 10;
            if (result < limit + digit) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result -= digit;
        }

        return negative ? result : -result;
    }
}
