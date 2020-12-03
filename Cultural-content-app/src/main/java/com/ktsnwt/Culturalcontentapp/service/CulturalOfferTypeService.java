package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;
import com.ktsnwt.Culturalcontentapp.repository.CulturalOfferTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CulturalOfferTypeService {
    
    @Autowired
    private CulturalOfferTypeRepository culturalOfferTypeRepository;


    public List<CulturalOfferType> findAll() {
        return culturalOfferTypeRepository.findAll();
    }

    public Optional<CulturalOfferType> findOne(Long id) {
        return culturalOfferTypeRepository.findById(id);
    }

    public CulturalOfferType findByName(String name) {
        return culturalOfferTypeRepository.findByName(name);
    }

    public CulturalOfferType create(CulturalOfferType newType) throws Exception {
        if (culturalOfferTypeRepository.findByName(newType.getName()) != null) {
            throw new Exception("Cultural category type with given name already exists!");
        }

        return culturalOfferTypeRepository.save(newType);
    }

    public void update(Long id, CulturalOfferTypeDTO updateDTO) throws Exception {
        Optional<CulturalOfferType> existingCulturalOfferType = culturalOfferTypeRepository.findById(id);
        if (existingCulturalOfferType.isEmpty()) {
            throw new Exception("Cultural offer type with given id doesn't exist!");
        }
        CulturalOfferType culturalOfferType = existingCulturalOfferType.get();
        culturalOfferType.setName(updateDTO.getName());

        culturalOfferTypeRepository.save(culturalOfferType);
    }


    public void delete(Long id) throws Exception {
        Optional<CulturalOfferType> existingCulturalOfferType = culturalOfferTypeRepository.findById(id);
        if (existingCulturalOfferType.isEmpty()) {
            throw new Exception("Cultural offer type with given id doesn't exist!");
        }

        culturalOfferTypeRepository.delete(existingCulturalOfferType.get());
    }


}
