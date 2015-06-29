package com.xqbase.java.util;

public final class TextUtils {

    public static boolean isEmpty(final CharSequence s) {
        return null == s || s.length() == 0;
    }

    public static boolean isBlank(final CharSequence s) {
        if (null == s) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
