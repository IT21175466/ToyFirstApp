package com.example.toyfirst;

public class Toy {
    private int id;
    private String title;
    private String price;
    private String quantity;
    private String category;

    public Toy(){

    }

    public Toy(int id, String title, String price, String quantity, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Toy(String title, String price, String quantity, String category) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
