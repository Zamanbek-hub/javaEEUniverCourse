package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "shopitems")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopItem {

    public ShopItem(String name, String description, String type, int price, int amount, int stars, String pictureUrl) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.stars = stars;
        this.pictureUrl = pictureUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name", length=500)
    private String name;

    @Column(name="description", length=500)
    private String description;

    @Column(name="type")
    private String type;

    @Column(name="price")
    private int price;

    @Column(name="amount")
    private int amount;

    @Column(name="stars")
    private int stars;

    @Column(name="pictureUrl")
    private String pictureUrl;




}
