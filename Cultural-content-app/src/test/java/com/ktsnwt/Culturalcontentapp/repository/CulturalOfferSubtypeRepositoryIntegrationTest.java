package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.CulturalOfferSubtype;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferSubtypeConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferSubtypeConstants.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CulturalOfferSubtypeRepositoryIntegrationTest {


    @Autowired
    CulturalOfferSubtypeRepository culturalOfferSubtypeRepository;

    @Test
    void testFindByName() {
        CulturalOfferSubtype existingCulturalOfferSubtype = culturalOfferSubtypeRepository.findByName(EXISTING_SUBTYPE_NAME);
        assertEquals(EXISTING_SUBTYPE_NAME, existingCulturalOfferSubtype.getName());
    }

    @Test
    void testFindByName_NotFound() {
        CulturalOfferSubtype notFoundCulturalOfferSubtype = culturalOfferSubtypeRepository.findByName(NEW_SUBTYPE_NAME);
        assertNull(notFoundCulturalOfferSubtype);
    }


    @Test
    void testFindById() {
        Optional<CulturalOfferSubtype> existingCulturalOfferSubtype = culturalOfferSubtypeRepository.findById(EXISTING_SUBTYPE_ID);

        assertTrue(existingCulturalOfferSubtype.isPresent());
        assertEquals(EXISTING_SUBTYPE_ID, existingCulturalOfferSubtype.get().getId());

    }
}