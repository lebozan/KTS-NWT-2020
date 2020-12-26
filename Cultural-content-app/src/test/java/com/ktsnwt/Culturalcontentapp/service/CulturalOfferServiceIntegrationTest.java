package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CulturalOfferServiceIntegrationTest {

    @Autowired
    CulturalOfferService culturalOfferService;

    @Test
    void testFindByName() {
        CulturalOffer existingCulturalOffer = culturalOfferService.findByName(EXISTING_COFFER_NAME2);

        assertEquals(EXISTING_COFFER_NAME2, existingCulturalOffer.getName());
    }
    
    @Test
    void testFindById() {
        Optional<CulturalOffer> existingCulturalOffer = culturalOfferService.findOne(EXISTING_COFFER_ID);

        assertTrue(existingCulturalOffer.isPresent());
        assertEquals(EXISTING_COFFER_ID, existingCulturalOffer.get().getId());
    }

    @Test
    @Rollback
    void testUpdate() throws Exception {
        CulturalOfferDTO updateDTO = new CulturalOfferDTO();
        updateDTO.setName(EXISTING_COFFER_NAME_UPDATE);
        updateDTO.setDescription(EXISTING_COFFER_DESCRIPTION_UPDATE);

        CulturalOffer updated = culturalOfferService.update(EXISTING_COFFER_ID, updateDTO);

        assertEquals(EXISTING_COFFER_NAME_UPDATE, updated.getName());
    }

    @Test
    @Rollback
    void testDelete() throws Exception {
        culturalOfferService.delete(DELETABLE_COFFER_ID);
    }

    @Test
    @Rollback
    void testCreate() throws Exception {
        CulturalOffer newCulturalOffer = new CulturalOffer(NEW_COFFER_NAME, null, null, NEW_COFFER_DESCRIPTION);
        CulturalOffer createdCulturalOffer = culturalOfferService.create(newCulturalOffer);

        assertEquals(NEW_COFFER_NAME, createdCulturalOffer.getName());

    }
}
