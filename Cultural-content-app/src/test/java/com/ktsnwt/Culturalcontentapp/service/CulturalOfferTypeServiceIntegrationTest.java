package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferTypeConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CulturalOfferTypeServiceIntegrationTest {

    @Autowired
    CulturalOfferTypeService culturalOfferTypeService;


    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(TYPE_PAGE_NUMBER, TYPE_PAGE_SIZE);
        Page<CulturalOfferType> culturalOfferTypesPage = culturalOfferTypeService.findAll(pageable);

        assertEquals(PAGE_NUMBER_OF_ELEMENTS, culturalOfferTypesPage.getNumberOfElements());

    }


    @Test
    void testFindByName() {
        CulturalOfferType existingCulturalOfferType = culturalOfferTypeService.findByName(EXISTING_TYPE_NAME);

        assertEquals(EXISTING_TYPE_NAME, existingCulturalOfferType.getName());
    }

    @Test
    void testFindById() {
        Optional<CulturalOfferType> existingCulturalOfferType = culturalOfferTypeService.findById(EXISTING_TYPE_ID);

        assertTrue(existingCulturalOfferType.isPresent());
        assertEquals(EXISTING_TYPE_ID, existingCulturalOfferType.get().getId());
    }

    @Test
    @Rollback
    void testCreate() throws Exception {
        CulturalOfferType newCulturalOfferType = new CulturalOfferType(NEW_TYPE_NAME);
        CulturalOfferType createdCulturalOfferType = culturalOfferTypeService.create(newCulturalOfferType);

        assertEquals(NEW_TYPE_NAME, createdCulturalOfferType.getName());

    }

    @Test
    @Rollback
    void testUpdate() throws Exception {
        CulturalOfferTypeDTO updateDTO = new CulturalOfferTypeDTO();
        updateDTO.setName(EXISTING_TYPE_NAME_UPDATE);

        CulturalOfferType updated = culturalOfferTypeService.update(EXISTING_TYPE_ID, updateDTO);

        assertEquals(EXISTING_TYPE_NAME_UPDATE, updated.getName());
    }

    @Test
    @Rollback
    void testDelete() throws Exception {
        culturalOfferTypeService.delete(DELETABLE_TYPE_ID);

    }
}