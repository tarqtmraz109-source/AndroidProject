package com.example.pricepeek;

public class ShopModel {
    private String name;
    private String address;
    private String phone;
    private String rating;
    private String hours;
    private int imageRes;

    public ShopModel(String name, String address, String phone, String rating, String hours, int imageRes) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.hours = hours;
        this.imageRes = imageRes;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getRating() { return rating; }
    public String getHours() { return hours; }
    public int getImageRes() { return imageRes; }
}
