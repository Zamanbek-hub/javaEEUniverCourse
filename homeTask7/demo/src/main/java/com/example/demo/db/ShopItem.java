package com.example.demo.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopItem {
    private Long id;
    private String name;
    private String description;
    private String type;
    private int price;
    private int amount;
    private int stars;
    private ArrayList<Boolean> startArr;
    private  String pictureUrl;

    public ShopItem(Long id, String name, String description, String type, int price, int amount, int stars, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.stars = stars;
        this.pictureUrl = pictureUrl;
    }

    public ShopItem(String name, String description, String type, int price, int amount, int stars, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.stars = stars;
        this.pictureUrl = pictureUrl;
    }
}
