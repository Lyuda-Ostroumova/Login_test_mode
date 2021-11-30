package ru.netology.domain;

public class Registration {
    private String login;
    private String password;
    private String status;

    public Registration (String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}


