package com.ruokapp.core;

public class RecipeRef {

    private int id;
    private String title;
    private String image;
    private String preparationMinutes;

    public RecipeRef(int id, String title, String image, String preparationMinutes) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.preparationMinutes = preparationMinutes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getPreparationMinutes() {
        return preparationMinutes;
    }
}
