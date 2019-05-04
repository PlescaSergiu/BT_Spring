package com.pentalog.BT.entity;


public class Context {

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Context.user = user;
    }

}
