package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferSubtypeDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferSubtype;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;
import com.ktsnwt.Culturalcontentapp.repository.CulturalOfferSubtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CulturalOfferSubtypeService {

    @Autowired
    private CulturalOfferSubtypeRepository culturalOfferSubtypeRepository;

    public Page<CulturalOfferSubtype> findAll(Pageable pageable) {
        return culturalOfferSubtypeRepository.findAll(pageable);
    }

    public Optional<CulturalOfferSubtype> findOne(Long id) {
        return culturalOfferSubtypeRepository.findById(id);
    }

    public CulturalOfferSubtype findByName(String name) {
        return culturalOfferSubtypeRepository.findByName(name);
    }

    public Optional<CulturalOfferSubtype> findById(Long id) {
        return culturalOfferSubtypeRepository.findById(id);
    }


    public CulturalOfferSubtype create(CulturalOfferSubtype newSubtype) throws Exception {
        if (culturalOfferSubtypeRepository.findByName(newSubtype.getName()) != null) {
            throw new Exception("Cultural category subtype with given name already exists!");
        }

        return culturalOfferSubtypeRepository.save(newSubtype);
    }

    public void update(Long id, CulturalOfferSubtypeDTO updateDTO) throws Exception {
        Optional<CulturalOfferSubtype> existingCulturalOfferType = culturalOfferSubtypeRepository.findById(id);
        if (existingCulturalOfferType.isEmpty()) {
            throw new Exception("Cultural offer subtype with given id doesn't exist!");
        }
        CulturalOfferSubtype culturalOfferSubtype = existingCulturalOfferType.get();
        culturalOfferSubtype.setName(updateDTO.getName());

        culturalOfferSubtypeRepository.save(culturalOfferSubtype);
    }


    public void delete(Long id) throws Exception {
        Optional<CulturalOfferSubtype> existingCulturalOfferSubtype = culturalOfferSubtypeRepository.findById(id);
        if (existingCulturalOfferSubtype.isEmpty()) {
            throw new Exception("Cultural offer subtype with given id doesn't exist!");
        }

        culturalOfferSubtypeRepository.delete(existingCulturalOfferSubtype.get());
    }

}
