package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.ImageDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;
import com.ktsnwt.Culturalcontentapp.model.Image;
import com.ktsnwt.Culturalcontentapp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }


    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    public Image create(Image newImage) throws Exception {

        return imageRepository.save(newImage);
    }


    public void delete(Long id) throws Exception {
        Optional<Image> existingImage = imageRepository.findById(id);
        if (existingImage.isEmpty()) {
            throw new Exception("Cultural offer type with given id doesn't exist!");
        }

        imageRepository.delete(existingImage.get());
    }
}
