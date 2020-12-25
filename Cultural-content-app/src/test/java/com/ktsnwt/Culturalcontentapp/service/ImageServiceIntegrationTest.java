package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.model.Image;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static com.ktsnwt.Culturalcontentapp.constants.ImageConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class ImageServiceIntegrationTest {

    @Autowired
    ImageService imageService;

    @Test
    void findAll() {
        List<Image> allImages = imageService.findAll();

        assertEquals(NUMBER_OF_EXISTING_IMAGES, allImages.size());
    }

    @Test
    void testFindById() {
        Optional<Image> existingImage = imageService.findById(EXISTING_IMAGE_ID);

        assertTrue(existingImage.isPresent());
        assertEquals(EXISTING_IMAGE_ID, existingImage.get().getId());
    }

    @Test
    void testFindByIdNotExisting() {
        Optional<Image> notFound = imageService.findById(NOT_EXISTING_IMAGE_ID);
        assertEquals(Optional.empty(), notFound);
    }


    @Test
    void testCreate() throws Exception {
        Image newImage = new Image();
        newImage.setAddressURL(NEW_IMAGE_ADDRESS);

        Image createdImage = imageService.create(newImage);
        assertEquals(NEW_IMAGE_ID, createdImage.getId());
        assertEquals(NEW_IMAGE_ADDRESS, createdImage.getAddressURL());

    }

    @Test
    void testDelete() {
        assertDoesNotThrow(() -> {imageService.delete(EXISTING_IMAGE_ID);});
    }

    @Test
    void testDeleteNotExisting() {
        assertThrows(Exception.class, () -> {imageService.delete(NOT_EXISTING_IMAGE_ID);});
    }
}