package com.example.toyfirst;

public class Shopping_Cart_Model {
    private int cart_id;
    private String cart_title;
    private String cart_price;

    public Shopping_Cart_Model(){

    }

    public Shopping_Cart_Model(int cart_id, String cart_title, String cart_price) {
        this.cart_id = cart_id;
        this.cart_title = cart_title;
        this.cart_price = cart_price;
    }

    public Shopping_Cart_Model(String cart_title, String cart_price) {
        this.cart_title = cart_title;
        this.cart_price = cart_price;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public String getCart_title() {
        return cart_title;
    }

    public void setCart_title(String cart_title) {
        this.cart_title = cart_title;
    }

    public String getCart_price() {
        return cart_price;
    }

    public void setCart_price(String cart_price) {
        this.cart_price = cart_price;
    }
}
