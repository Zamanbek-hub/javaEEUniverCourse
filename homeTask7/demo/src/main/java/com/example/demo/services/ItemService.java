package com.example.demo.services;

import com.example.demo.entities.ShopItem;

import java.util.List;

public interface ItemService {
    ShopItem addItem(ShopItem item);
    List<ShopItem> getAllItems();
    ShopItem getItem(Long id);
    void deleteItem(Long id);
    ShopItem saveItem(ShopItem item);


    List<ShopItem> findAllByTypeEquals(String type);
    List<ShopItem> findDistinctByType(String type);
    boolean existsShopItemByIdEquals(Long id);

    List<ShopItem> findAllByNameContainsOrderByPriceAsc(String name);
    List<ShopItem> findAllByNameContainsOrderByPriceDesc(String name);
    List<ShopItem> findAllByNameContainsAndPriceBetweenOrderByPriceAsc(String name,int price1, int price2);
    List<ShopItem> findAllByNameContainsAndPriceBetweenOrderByPriceDesc(String name,int price1, int price2);

}
