package com.example.demo.repositories;

import com.example.demo.entities.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ShopItemRepository extends JpaRepository<ShopItem, Long> {
    List<ShopItem> findAllByTypeEquals(String type);

    List<ShopItem> findDistinctByType(String type);
    boolean existsShopItemByIdEquals(Long id);

    List<ShopItem> findAllByNameContainsOrderByPriceAsc(String name);
    List<ShopItem> findAllByNameContainsOrderByPriceDesc(String name);
    List<ShopItem> findAllByNameContainsAndPriceBetweenOrderByPriceAsc(String name,int price1, int price2);
    List<ShopItem> findAllByNameContainsAndPriceBetweenOrderByPriceDesc(String name, int price1, int price2);
}
