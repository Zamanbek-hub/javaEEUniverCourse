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
}
