package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferSubtypeDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferSubtype;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferSubtypeConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CulturalOfferSubtypeServiceIntegrationTest {

    @Autowired
    CulturalOfferSubtypeService culturalOfferSubtypeService;

    @Autowired
    CulturalOfferTypeService culturalOfferTypeService;

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(SUBTYPE_PAGE_NUMBER, SUBTYPE_PAGE_SIZE);
        Page<CulturalOfferSubtype> culturalOfferSubtypePage = culturalOfferSubtypeService.findAll(pageable);

        assertEquals(PAGE_NUMBER_OF_ELEMENTS, culturalOfferSubtypePage.getNumberOfElements());

    }

    @Test
    void testFindByName() {
        CulturalOfferSubtype existingCulturalOfferSubtype = culturalOfferSubtypeService.findByName(EXISTING_SUBTYPE_NAME);

        assertEquals(EXISTING_SUBTYPE_NAME, existingCulturalOfferSubtype.getName());
    }

    @Test
    void testFindById() {
        Optional<CulturalOfferSubtype> existingCulturalOfferSubtype = culturalOfferSubtypeService.findById(EXISTING_SUBTYPE_ID);

        assertTrue(existingCulturalOfferSubtype.isPresent());
        assertEquals(EXISTING_SUBTYPE_ID, existingCulturalOfferSubtype.get().getId());
    }

    @Test
    @Rollback
    void testCreate() throws Exception {
        CulturalOfferType newSubtypeType = culturalOfferTypeService.findByName(NEW_SUBTYPE_TYPE_NAME);
        CulturalOfferSubtype newCulturalOfferSubtype = new CulturalOfferSubtype(NEW_SUBTYPE_NAME, newSubtypeType);
        CulturalOfferSubtype created = culturalOfferSubtypeService.create(newCulturalOfferSubtype);

        assertEquals(NEW_SUBTYPE_NAME, created.getName());
        assertEquals(NEW_SUBTYPE_TYPE_NAME, created.getType().getName());
    }

    @Test
    @Rollback
    void testUpdate() throws Exception {
        CulturalOfferSubtypeDTO updateDTO = new CulturalOfferSubtypeDTO();
        updateDTO.setName(EXISTING_SUBTYPE_NAME_UPDATE);

        CulturalOfferSubtype updated = culturalOfferSubtypeService.update(EXISTING_SUBTYPE_ID, updateDTO);

        assertEquals(EXISTING_SUBTYPE_NAME_UPDATE, updated.getName());

    }

    @Test
    @Rollback
    void testDelete() {
        assertDoesNotThrow(() -> {culturalOfferSubtypeService.delete(EXISTING_SUBTYPE_ID_DELETE);});
    }

    @Test
    @Rollback
    void testDeleteThrowsError() {
        assertThrows(DataIntegrityViolationException.class, () -> {culturalOfferSubtypeService.delete(EXISTING_SUBTYPE_ID);});
    }
}