package com.example.demo.controller;

import com.example.demo.db.DBManager;
import com.example.demo.db.ShopItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model, @RequestParam(name = "type", defaultValue = "all", required = false) String type){
        ArrayList<ShopItem> items = DBManager.getAllItems();

        if(! type.equals("all")) {
            ArrayList<ShopItem> items2 = new ArrayList<>();
            for (ShopItem item : items) {
                if (item.getType().equals(type)) {
                    items2.add(item);
                }
            }
            items = items2;
        }


        model.addAttribute("items", items);
        return "index";
    }

    @PostMapping(value="/add_item")
    public String addItem(@RequestParam(name = "add_name", defaultValue = "No Name") String name,
                          @RequestParam(name="add_description", defaultValue = "No Description") String description,
                          @RequestParam(name="add_type", defaultValue = "")  String type,
                          @RequestParam(name="add_price", defaultValue = "1")  int price,
                          @RequestParam(name="add_amount", defaultValue = "1")  int amount,
                          @RequestParam(name="add_stars", defaultValue = "1")  int stars,
                          @RequestParam(name="add_picture_url", defaultValue = "No URL") String picture_url){
        DBManager.addItem(new ShopItem(name,description,type,price, amount, stars, picture_url));
        return "redirect:/";
    }
}
