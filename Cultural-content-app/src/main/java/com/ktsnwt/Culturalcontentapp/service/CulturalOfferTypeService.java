package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.repository.CulturalOfferTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CulturalOfferTypeService {
    
    @Autowired
    private CulturalOfferTypeRepository culturalOfferTypeRepository;

}
