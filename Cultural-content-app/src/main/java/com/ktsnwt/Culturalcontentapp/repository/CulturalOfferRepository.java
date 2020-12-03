package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CulturalOfferRepository extends JpaRepository<CulturalOffer, Long> {

    CulturalOffer findByName(String name);
    
}
