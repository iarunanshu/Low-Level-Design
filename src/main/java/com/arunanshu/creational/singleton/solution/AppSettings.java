package com.arunanshu.creational.singleton.solution;

public class AppSettings {
    private static AppSettings instance;

    private String databaseUrl;
    private String apiKey;

    private AppSettings(){
        databaseUrl="jdbc:mysql//localhost:3306";
        apiKey="12345-abcde";
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public static AppSettings getInstance() {
        if(instance==null){
        instance=new AppSettings();
        }
        return instance;
    }

    public String getApiKey() {
        return apiKey;
    }
}
