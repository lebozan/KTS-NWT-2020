package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferTypeService;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferTypeConstants.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CulturalOfferTypeRepositoryIntegrationTest {

    @Autowired
    CulturalOfferTypeRepository culturalOfferTypeRepository;


    @Test
    void testFindByName() {
        CulturalOfferType existingCulturalOfferType = culturalOfferTypeRepository.findByName(EXISTING_TYPE_NAME);
        assertEquals(EXISTING_TYPE_NAME, existingCulturalOfferType.getName());
    }


    @Test
    void testFindByName_NotFound() {
        CulturalOfferType existingCulturalOfferType = culturalOfferTypeRepository.findByName(NEW_TYPE_NAME);
        assertNull(existingCulturalOfferType);
    }

    @Test
    void testFindById() {
        Optional<CulturalOfferType> existingCulturalOfferType = culturalOfferTypeRepository.findById(EXISTING_TYPE_ID);

        assertTrue(existingCulturalOfferType.isPresent());
        assertEquals(EXISTING_TYPE_ID, existingCulturalOfferType.get().getId());

    }
}