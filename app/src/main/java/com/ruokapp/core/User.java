package com.ruokapp.core;

public class User {

    private long id;
    private String name;
    private String email;
    private boolean pref_vegan;
    private boolean pref_vegetarian;
    private boolean pref_gluten_free;
    private boolean pref_chinese;
    private boolean pref_italian;
    private boolean pref_japanese;
    private static User user = null;

    private static void createUser() {
        user = new User();
    }

    public static User getInstanceUser(){
        if(user == null){
            createUser();
        }
        return user;
    }

    public void setUser(long id, String username, String email){
        this.id = id;
        this.name = username;
        this.email = email;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public boolean isPref_vegan() { return pref_vegan; }

    public void setPref_vegan(boolean pref_vegan) { this.pref_vegan = pref_vegan; }

    public boolean isPref_vegetarian() { return pref_vegetarian; }

    public void setPref_vegetarian(boolean pref_vegetarian) { this.pref_vegetarian = pref_vegetarian; }

    public boolean isPref_gluten_free() { return pref_gluten_free; }

    public void setPref_gluten_free(boolean pref_gluten_free) { this.pref_gluten_free = pref_gluten_free; }

    public boolean isPref_chinese() { return pref_chinese; }

    public void setPref_chinese(boolean pref_chinese) { this.pref_chinese = pref_chinese; }

    public boolean isPref_italian() { return pref_italian; }

    public void setPref_italian(boolean pref_italian) { this.pref_italian = pref_italian; }

    public boolean isPref_japanese() { return pref_japanese; }

    public void setPref_japanese(boolean pref_japanese) { this.pref_japanese = pref_japanese; }

}
