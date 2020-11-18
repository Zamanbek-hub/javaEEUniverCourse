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

@Controller(value = "/admin/countries")
public class CountryController {

    @Autowired
    private BrandService brandService;

    @GetMapping(value = "/admin/countries")
    public String adminCountries(Model model){
        List<Country> countries = brandService.getAllCountries();
        model.addAttribute("countries", countries);
        return "admin/country_tables";
    }

    @PostMapping(value = "/admin/countries/save")
    public String saveCountry(@RequestParam(name = "add_name", defaultValue = "No Name") String name,
                               @RequestParam(name="add_code", defaultValue = "") String code){

        brandService.saveCountry(new Country(name, code));
        return "redirect:/admin/countries";

    }



    @PostMapping(value = "/admin/countries/update")
    public String updateCountry(@RequestParam(name = "update_id", defaultValue = "-1") Long id,
                                 @RequestParam(name = "update_name", defaultValue = "No Name") String name,
                                 @RequestParam(name="update_code", defaultValue = "") String code){
        Country ct = brandService.getCountry(id);
        if(ct != null) {
            brandService.saveCountry(new Country(id, name, code));
            return "redirect:/admin/countries";
        }

        return "error";
    }

}
