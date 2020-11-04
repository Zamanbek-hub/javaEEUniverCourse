package com.example.demo.services.impl;

import com.example.demo.entities.ShopItem;
import com.example.demo.repositories.ShopItemRepository;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Override
    public ShopItem addItem(ShopItem item) {
        return shopItemRepository.save(item);
    }

    @Override
    public List<ShopItem> getAllItems() {
        return shopItemRepository.findAll();
    }

    @Override
    public ShopItem getItem(Long id) {
        return shopItemRepository.getOne(id);
    }

    @Override
    public void deleteItem(Long id) {
        shopItemRepository.deleteById(id);
    }

    @Override
    public ShopItem saveItem(ShopItem item) {
        return shopItemRepository.save(item);
    }


    @Override
    public List<ShopItem> findAllByTypeEquals(String type) {
        return shopItemRepository.findAllByTypeEquals(type);
    }

    @Override
    public boolean existsShopItemByIdEquals(Long id) {
        return shopItemRepository.existsShopItemByIdEquals(id);
    }

    @Override
    public List<ShopItem> findDistinctByType(String type) {
        return shopItemRepository.findDistinctByType(type);
    }


    @Override
    public List<ShopItem> findAllByNameContainsOrderByPriceAsc(String name) {
        return shopItemRepository.findAllByNameContainsOrderByPriceAsc(name);
    }

    @Override
    public List<ShopItem> findAllByNameContainsOrderByPriceDesc(String name) {
        return shopItemRepository.findAllByNameContainsOrderByPriceDesc(name);
    }

    @Override
    public List<ShopItem> findAllByNameContainsAndPriceBetweenOrderByPriceAsc(String name,int price1, int price2) {
        return shopItemRepository.findAllByNameContainsAndPriceBetweenOrderByPriceAsc(name, price1, price2);
    }

    @Override
    public List<ShopItem> findAllByNameContainsAndPriceBetweenOrderByPriceDesc(String name,int price1, int price2) {
        return shopItemRepository.findAllByNameContainsAndPriceBetweenOrderByPriceDesc(name, price1, price2);
    }
}
