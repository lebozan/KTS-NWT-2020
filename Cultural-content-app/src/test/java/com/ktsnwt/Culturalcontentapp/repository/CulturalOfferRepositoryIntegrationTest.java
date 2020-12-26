package com.ktsnwt.Culturalcontentapp.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CulturalOfferRepositoryIntegrationTest {

    @Autowired
    CulturalOfferRepository culturalOfferRepository;

    @Test
    void testFindByName() {
        CulturalOffer existingCulturalOffer = culturalOfferRepository.findByName(EXISTING_COFFER_NAME2);
        assertEquals(EXISTING_COFFER_NAME2, existingCulturalOffer.getName());
    }

    @Test
    void testFindByName_NotFound() {
        CulturalOffer existingCulturalOffer = culturalOfferRepository.findByName(EXISTING_COFFER_NAME);
        assertNull(existingCulturalOffer);
    }

    @Test
    void testFindById() {
        Optional<CulturalOffer> existingCulturalOffer = culturalOfferRepository.findById(EXISTING_COFFER_ID);

        assertTrue(existingCulturalOffer.isPresent());
        assertEquals(EXISTING_COFFER_ID, existingCulturalOffer.get().getId());

    }
}
