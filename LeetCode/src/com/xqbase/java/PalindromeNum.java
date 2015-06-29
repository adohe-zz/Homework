package com.xqbase.java;

/**
 * LeetCodeNine -- Palindrome Number
 *
 * @author Tony He
 */
public class PalindromeNum {

    public static void main(String[] args) {
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(123321));
        System.out.println(isPalindrome(12344321));
        System.out.println(isPalindrome(12233220));
        System.out.println(isPalindrome(12233221));
    }

    private static boolean isPalindrome(int x) { // from head to middle, from tail to middle
        if (x < 0)
            return false;
        int len = 1;
        while ( x / len >= 10) {
            len *= 10;
        }

        while (x > 0) {
            int head = x / len;
            int tail = x % 10;
            if (head != tail)
                return false;
            x = (x % len) / 10;
            len = len / 100;
        }
        return true;
    }


}
