package com.yqc.entity;

/**
 * Created by yangqc on 2017/4/9.
 */
public class User {

    private String name;
    private int age;
    private String favorite;

    public User() {
    }

    public User(String name, int age, String favorite) {
        this.name = name;
        this.age = age;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
