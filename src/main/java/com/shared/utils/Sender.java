package com.shared.utils;

public class Sender {
    private String login;
    private String password;
    private boolean isLogin;

    public Sender(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Sender(String login, String password, boolean isLogin) {
        this.login = login;
        this.password = password;
        this.isLogin = isLogin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
