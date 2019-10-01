package com.ruokapp.core;

public class Food {

    private String name;
    private String time;

    public Food(String name, String time){
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
