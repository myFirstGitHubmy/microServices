package com.shared.utils;

public class EmailHandler {
    private final String WWW = "www.";
    public String getEmail(String email) {
        String changedEmail = email;
        if (changedEmail.startsWith(WWW)) {
            changedEmail = email.substring(WWW.length(), changedEmail.length());
        }

        if (changedEmail.indexOf(":") > 0) {
            changedEmail.substring(0, changedEmail.indexOf(":"));
        }
        return changedEmail;
    }
}
