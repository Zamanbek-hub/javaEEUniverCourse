package com.example.demo.controller;

import com.example.demo.entities.*;
import com.example.demo.services.BrandService;
import com.example.demo.services.ItemService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin/countries")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String adminCountries(Model model){
        List<Country> countries = brandService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("currentUser", getUserData());

        return "admin/country_tables";
    }

    @PostMapping(value = "/admin/countries/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String saveCountry(@RequestParam(name = "add_name", defaultValue = "No Name") String name,
                               @RequestParam(name="add_code", defaultValue = "") String code, Model model){
        System.out.println("I am here");
        model.addAttribute("currentUser", getUserData());
        brandService.saveCountry(new Country(name, code));
        return "redirect:/admin/countries";

    }



    @PostMapping(value = "/admin/countries/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
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


    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            return userService.getUserByEmail(secUser.getUsername());
        }

        return null;
    }

}
