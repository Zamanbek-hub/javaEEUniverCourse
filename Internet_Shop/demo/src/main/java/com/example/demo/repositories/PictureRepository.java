package com.example.demo.repositories;


import com.example.demo.entities.Category;
import com.example.demo.entities.Item;
import com.example.demo.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findAllByItem(Item item);
}
