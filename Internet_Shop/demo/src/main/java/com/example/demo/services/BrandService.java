package com.example.demo.services;

import com.example.demo.entities.Country;

import java.util.List;

public interface BrandService {
    List<Country> getAllCountries();
    Country addCountry(Country country);
    Country saveCountry(Country country);
    Country getCountry(Long id);

}
