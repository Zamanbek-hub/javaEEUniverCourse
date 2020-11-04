package com.example.demo.controller;

import com.example.demo.entities.ShopItem;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/")
    public String index(Model model, @RequestParam(name = "type", defaultValue = "all", required = false) String type){
        List<ShopItem> items;

        if(! type.equals("all"))
            items = itemService.findAllByTypeEquals(type);
        else
            items = itemService.getAllItems();

        model.addAttribute("items", items);
        model.addAttribute("types", items);
        return "index";
    }

    @GetMapping(value = "/admin")
    public String adminIndex(Model model, @RequestParam(name = "type", defaultValue = "all", required = false) String type){
        List<ShopItem> items = itemService.getAllItems();
        List<String> types = new ArrayList<>();

        for(ShopItem item: items)
            if(! types.contains(item.getType()))
                types.add(item.getType());


        if(! type.equals("all"))
            items = itemService.findAllByTypeEquals(type);

        model.addAttribute("items", items);
        model.addAttribute("types", types);
        return "adminIndex";
    }


    @GetMapping(value = "/result")
    public String searchResult(Model model,
                               @RequestParam(name = "word", defaultValue = "all", required = false) String word,
                               @RequestParam(name = "orderBy", defaultValue = "asc", required = false) String orderBy,
                               @RequestParam(name = "price_from", defaultValue = "0", required = false) int priceFrom,
                               @RequestParam(name = "price_to", defaultValue = "0", required = false) int priceTo){
        if(word.equals("all"))
            return "redirect:/";

        List<ShopItem> items;

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
        return "searchResult";
    }


    @GetMapping(value = "/item")
    public String itemDetail(Model model, @RequestParam(name = "id", defaultValue = "1", required = true) Long id){
        ShopItem item = itemService.getItem(id);
        if(item != null) {
            model.addAttribute("item", item);
            return "itemDetail";
        }

        return "error";
    }

    @GetMapping(value = "/itemAdmin")
    public String itemDetailAdmin(Model model, @RequestParam(name = "id", defaultValue = "1", required = true) Long id){
        ShopItem item = itemService.getItem(id);
        if(item != null) {
            model.addAttribute("item", item);
            return "itemDetailAdmin";
        }

        return "error";
    }

    @PostMapping(value="/add_item")
    public String addItem(@RequestParam(name = "add_name", defaultValue = "No Name") String name,
                          @RequestParam(name="add_description", defaultValue = "No Description") String description,
                          @RequestParam(name="add_type", defaultValue = "")  String type,
                          @RequestParam(name="add_price", defaultValue = "1")  int price,
                          @RequestParam(name="add_amount", defaultValue = "1")  int amount,
                          @RequestParam(name="add_stars", defaultValue = "1")  int stars,
                          @RequestParam(name="add_picture_url", defaultValue = "No URL") String picture_url){
        itemService.addItem(new ShopItem(name,description,type,price, amount, stars, picture_url));
        return "redirect:/admin";
    }

    @PostMapping(value="/update_item")
    public String updateItem(@RequestParam(name = "id", defaultValue = "0") Long id,
                             @RequestParam(name = "add_name", defaultValue = "No Name") String name,
                          @RequestParam(name="add_description", defaultValue = "No Description") String description,
                          @RequestParam(name="add_type", defaultValue = "")  String type,
                          @RequestParam(name="add_price", defaultValue = "1")  int price,
                          @RequestParam(name="add_amount", defaultValue = "1")  int amount,
                          @RequestParam(name="add_stars", defaultValue = "1")  int stars,
                          @RequestParam(name="add_picture_url", defaultValue = "No URL") String picture_url){
        if(itemService.existsShopItemByIdEquals(id)) {
            itemService.saveItem(new ShopItem(id, name, description, type, price, amount, stars, picture_url));
            return "redirect:/admin";
        }
        return "error";
    }

    @PostMapping(value="/delete_item")
    public String updateItem(@RequestParam(name = "id", defaultValue = "0") Long id){
        if(itemService.existsShopItemByIdEquals(id)) {
            itemService.deleteItem(id);
            return "redirect:/admin";
        }
        return "error";
    }

}
