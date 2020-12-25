package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.Image;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.ktsnwt.Culturalcontentapp.constants.ImageConstants.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
class ImageRepositoryIntegrationTest {

    @Autowired
    ImageRepository imageRepository;

    @Test
    void testFindAll() {
        List<Image> allImages = imageRepository.findAll();
        assertEquals(NUMBER_OF_EXISTING_IMAGES, allImages.size());
    }


    @Test
    void testFindById() {
        Optional<Image> existingImage = imageRepository.findById(EXISTING_IMAGE_ID);
        assertTrue(existingImage.isPresent());
        assertEquals(EXISTING_IMAGE_ID, existingImage.get().getId());
    }
}
