package com.example.demo.services;

import com.example.demo.entities.Item;
import com.example.demo.entities.Picture;

import java.util.List;


public interface PictureService {
    Picture savePicture(Picture picture);
    List findPicturesByItem(Item item);
    void delete(Picture picture);
    Picture findById(Long id);
}
