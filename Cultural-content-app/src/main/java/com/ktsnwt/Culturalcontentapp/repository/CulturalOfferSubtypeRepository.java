package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.CulturalOfferSubtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CulturalOfferSubtypeRepository extends JpaRepository<CulturalOfferSubtype, Long> {

    CulturalOfferSubtype findByName(String name);
    
}
