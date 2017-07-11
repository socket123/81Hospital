package com.chusu.platform.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordEncryptor {
    private static final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    private static final String SLAT = "Yahuan-180";

    public static String encyptString(String input) {
        return passwordEncryptor.encryptPassword(input + SLAT);
    }

    public static boolean checkPassword(String plainPassword, String encryptedPassword) {
        return passwordEncryptor.checkPassword(plainPassword + SLAT, encryptedPassword);
    }

}
