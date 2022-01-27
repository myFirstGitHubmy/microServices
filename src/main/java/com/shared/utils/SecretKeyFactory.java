package com.shared.utils;

import org.mindrot.jbcrypt.BCrypt;

public class SecretKeyFactory {
    private final String salt = BCrypt.gensalt(12);

    public String encoderKey(String password){
        return BCrypt.hashpw(password, salt);
    }

    public boolean verifyPass(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }
}
