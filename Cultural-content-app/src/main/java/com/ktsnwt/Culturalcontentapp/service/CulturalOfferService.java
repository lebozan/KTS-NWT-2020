package com.ktsnwt.Culturalcontentapp.service;

import java.util.List;
import java.util.Optional;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.repository.CulturalOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CulturalOfferService {

    @Autowired
    private CulturalOfferRepository culturalOfferRepository;

    public List<CulturalOffer> findAll() {
        return culturalOfferRepository.findAll();
    }

    public Optional<CulturalOffer> findOne(Long id) {
        return culturalOfferRepository.findById(id);
    }

    public CulturalOffer findByName(String name) { 
        return culturalOfferRepository.findByName(name); 
    }

    public CulturalOffer create(CulturalOffer newOffer) throws Exception {
        if (culturalOfferRepository.findByName(newOffer.getName()) != null) {
            throw new Exception("Cultural offer with same name already exists!");
        }

        return culturalOfferRepository.save(newOffer);
    }

    public void delete(Long id) throws Exception {
        Optional<CulturalOffer> existingCulturalOffer = culturalOfferRepository.findById(id);
        if (existingCulturalOffer.isEmpty()) {
            throw new Exception("Cultural offer with this id doesn't exist!");
        }

        culturalOfferRepository.delete(existingCulturalOffer.get());
    }

    public CulturalOffer update(Long id, CulturalOfferDTO updateDTO) throws Exception {
        Optional<CulturalOffer> existingCulturalOffer = culturalOfferRepository.findById(id);
        if (existingCulturalOffer.isEmpty()) {
            throw new Exception("Cultural offer with this id doesn't exist!");
        }
        CulturalOffer culturalOffer = existingCulturalOffer.get();
        culturalOffer.setName(updateDTO.getName());
        culturalOffer.setImages(updateDTO.getImages());
        culturalOffer.setLocation(updateDTO.getLocation());
        culturalOffer.setDescription(updateDTO.getDescription());

        return culturalOfferRepository.save(culturalOffer);
    }

    
}
