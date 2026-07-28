package com.example.pricepeek;

public class Category {
    private String name;
    private int iconRes;
    private Class<?> activityClass;

    public Category(String name, int iconRes, Class<?> activityClass) {
        this.name = name;
        this.iconRes = iconRes;
        this.activityClass = activityClass;
    }

    public String getName() { return name; }
    public int getIconRes() { return iconRes; }
    public Class<?> getActivityClass() { return activityClass; }
}
