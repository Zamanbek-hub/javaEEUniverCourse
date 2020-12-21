package com.example.demo.repositories;

import com.example.demo.entities.Brand;
import com.example.demo.entities.Category;
import com.example.demo.entities.Item;
import com.example.demo.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByTypeEquals(String type);

    List<Item> findDistinctByType(String type);
    boolean existsItemByIdEquals(Long id);

    List<Item> findAllByNameContainsOrderByPriceAsc(String name);
    List<Item> findAllByNameContainsOrderByPriceDesc(String name);
    List<Item> findAllByNameContainsAndPriceBetweenOrderByPriceAsc(String name, int price1, int price2);
    List<Item> findAllByNameContainsAndPriceBetweenOrderByPriceDesc(String name, int price1, int price2);

//    List<Item> findAllByNameContainsOrPriceBetweenOrBrandEqualsOrderByPriceAsc(String name, int price_from, int price_to, Brand br);
//    List<Item> findAllByNameContainsOrPriceBetweenOrBrandEqualsOrderByPriceDesc(String name, int price_from, int price_to, Brand br);

    List<Item> findAllByNameContainsAndPriceBetweenAndBrandEqualsOrderByPriceAsc(String name, int price_from, int price_to, Brand br);
    List<Item> findAllByNameContainsAndPriceBetweenAndBrandEqualsOrderByPriceDesc(String name, int price_from, int price_to, Brand br);


    List<Item> findAllByCategoriesContains(Category cat);

    List<Item> findAllByNameContains(String name);

}
