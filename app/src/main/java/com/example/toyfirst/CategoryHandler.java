package com.example.toyfirst;

public class CategoryHandler {

    private String categoryName;

    public CategoryHandler(){

    }

    public CategoryHandler(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
