package com.arunanshu.creational.singleton.solution;

public class SingletonPattern {
    public static void main(String[] args) {
        AppSettings appSettings=AppSettings.getInstance();
        AppSettings copyappsetting=AppSettings.getInstance();

        System.out.println(appSettings==copyappsetting);
    }
}
