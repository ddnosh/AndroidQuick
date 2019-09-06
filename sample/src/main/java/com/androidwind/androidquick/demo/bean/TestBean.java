package com.androidwind.androidquick.demo.bean;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TestBean {

    private String login;
    private String id;
    private String avatar_url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "login='" + login + '\'' +
                ", id='" + id + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
