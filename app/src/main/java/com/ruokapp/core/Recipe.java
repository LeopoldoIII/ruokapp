package com.ruokapp.core;

public class Recipe {

    private int id;
    private String title;
    private String image;
    private String readyInMinutes;
    private static Recipe recipe;

    public static Recipe getInstance(){
        if (recipe==null){
            recipe = new Recipe();
        }
        return recipe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setReadyInMinutes(String readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getImage() {
        return image;
    }
}
