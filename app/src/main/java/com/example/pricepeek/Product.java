package com.example.pricepeek;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String shop;
    private String price;
    private int imageRes;

    public Product(String name, String shop, String price, int imageRes) {
        this.name = name;
        this.shop = shop;
        this.price = price;
        this.imageRes = imageRes;
    }

    public String getName() { return name; }
    public String getShop() { return shop; }
    public String getPrice() { return price; }
    public int getImageRes() { return imageRes; }
}
