package com.example.demo.controller;

import com.example.demo.entities.Brand;
import com.example.demo.entities.Category;
import com.example.demo.entities.Country;
import com.example.demo.entities.Item;
import com.example.demo.services.BrandService;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "/")
public class HomeController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private BrandService brandService;

    @GetMapping(value = "/")
    public String index(Model model, @RequestParam(name = "category_id", defaultValue = "-1", required = false) Long category_id){
        List<Item> items;
        Category cat = itemService.getCategory(category_id);

        if(category_id > 0 && cat != null)
            items = itemService.findAllByCategoriesContains(cat);

        else
            items = itemService.getAllItems();


        model.addAttribute("items", items);
        model.addAttribute("categories", itemService.getAllCategories());
        model.addAttribute("brands", itemService.getAllBrands());
        return "user/index";
    }


    @GetMapping(value = "/search")
    public String advancedSearch(Model model,
                                 @RequestParam(name = "name", defaultValue = "all", required = false) String name,
                                 @RequestParam(name = "brand_id", defaultValue = "-1", required = false) Long brand_id,
                                 @RequestParam(name = "price_from", defaultValue = "0", required = false) int price_from,
                                 @RequestParam(name = "price_to", defaultValue = "1000000000", required = false) int price_to,
                                 @RequestParam(name = "order", defaultValue = "asc", required = false) String order){

        List<Item> items;

        if (order.equals("asc"))
            items = itemService.findAllByNameContainsAndPriceBetweenAndBrandEqualsOrderByPriceAsc(name, price_from, price_to, itemService.getBrand(brand_id));
        else
            items = itemService.findAllByNameContainsAndPriceBetweenAndBrandEqualsOrderByPriceDesc(name, price_from, price_to, itemService.getBrand(brand_id));


        System.out.println("Check to null = " + items);
        System.out.println("price_from = " + price_from);
        System.out.println("price_to = " + price_to);
        if(items.size() == 0 && price_from == 0 && price_to == 1000000000) {
            items = itemService.findAllByNameContains(name);
            System.out.println("Name = " + items);

        }

        if (! items.isEmpty()) {
            brand_id = items.get(0).getBrand().getId();
        }

//        if(items == null)
//            items = itemService.getAllItems();


        model.addAttribute("items", items);
        model.addAttribute("categories", itemService.getAllCategories());
        model.addAttribute("brands", itemService.getAllBrands());
        model.addAttribute("brand_id", brand_id);
        return "user/advancedSearch";
    }


    @GetMapping(value = "/result")
    public String searchResult(Model model,
                               @RequestParam(name = "word", defaultValue = "all", required = false) String word,
                               @RequestParam(name = "orderBy", defaultValue = "asc", required = false) String orderBy,
                               @RequestParam(name = "price_from", defaultValue = "0", required = false) int priceFrom,
                               @RequestParam(name = "price_to", defaultValue = "0", required = false) int priceTo){
        if(word.equals("all"))
            return "redirect:/";

        List<Item> items;

        if(priceTo == 0)
            priceTo = 100000000;

        System.out.println("price_from = " + priceFrom);
        System.out.println("price_to = " + priceTo);


        if(orderBy.equals("desc"))
            items = itemService.findAllByNameContainsAndPriceBetweenOrderByPriceDesc(word, priceFrom, priceTo);

        else
            items = itemService.findAllByNameContainsAndPriceBetweenOrderByPriceAsc(word, priceFrom, priceTo);



        model.addAttribute("items", items);
        model.addAttribute("word", word);
        return "user/searchResult";
    }


    @GetMapping(value = "/item")
    public String itemDetail(Model model, @RequestParam(name = "id", defaultValue = "1", required = true) Long id){
        Item item = itemService.getItem(id);
        if(item != null) {
            model.addAttribute("item", item);
            model.addAttribute("categories", itemService.getAllCategories());
            model.addAttribute("brands", itemService.getAllBrands());
            return "user/itemDetail";
        }

        return "error";
    }

//    @GetMapping(value = "/itemAdmin")
//    public String itemDetailAdmin(Model model, @RequestParam(name = "id", defaultValue = "1", required = true) Long id){
//        Item item = itemService.getItem(id);
//        if(item != null) {
//            List<Brand> brands = itemService.getAllBrands();
//            model.addAttribute("brands", brands);
//            model.addAttribute("item", item);
//            return "user/itemDetailAdmin";
//        }
//
//        return "error";
//    }







}
