package com.example.demo.services.impl;

import com.example.demo.entities.Item;
import com.example.demo.entities.Picture;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.PictureRepository;
import com.example.demo.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public Picture savePicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    public List<Picture> findPicturesByItem(Item item){
        return pictureRepository.findAllByItem(item);
    }

    public void delete(Picture picture){
        pictureRepository.delete(picture);
    }

    public Picture findById(Long id){
        return pictureRepository.getOne(id);
    }
}
