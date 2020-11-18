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

@Controller(value = "/admin")
public class AdminController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private BrandService brandService;

    @GetMapping(value = "/admin")
    public String adminIndex(Model model){
        return "admin/adminIndex";
    }

}
