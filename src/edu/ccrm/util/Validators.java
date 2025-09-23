package edu.ccrm.util;

import java.util.regex.Pattern;

/**
 * Validator utilities with regex.
 */
public class Validators {
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static boolean isEmail(String s) {
        return s != null && EMAIL.matcher(s).matches();
    }

    public static boolean isNotBlank(String s) {
        return s != null && !s.isBlank();
    }
}
