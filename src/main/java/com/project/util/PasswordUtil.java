package com.project.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String password, String hash) {
        if (password == null || hash == null) {
            return false;
        }
        return BCrypt.checkpw(password, hash);
    }
}
