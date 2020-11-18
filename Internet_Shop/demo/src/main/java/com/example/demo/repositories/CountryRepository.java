package com.example.demo.repositories;

import com.example.demo.entities.Country;
import com.example.demo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country, Long> {
}
