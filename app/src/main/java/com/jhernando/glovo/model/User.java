package com.jhernando.glovo.model;

public class User {
    private int id;
    private String email;
    private String nickname;
    private String password;
    private String tlfn;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public User(int id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public User(int id, String email, String nickname, String password, String tlfn) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.tlfn = tlfn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTlfn() {
        return tlfn;
    }

    public void setTlfn(String tlfn) {
        this.tlfn = tlfn;
    }
}
