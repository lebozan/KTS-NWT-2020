package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.repository.CulturalOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CulturalOfferService {

    @Autowired
    private CulturalOfferRepository culturalOfferRepository;

    
}
