package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.RatingDTO;
import com.ktsnwt.Culturalcontentapp.model.Rating;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.ktsnwt.Culturalcontentapp.constants.RatingConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class RatingServiceIntegrationTest {

    @Autowired
    RatingService ratingService;


    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(RATING_PAGE_NUMBER, RATING_PAGE_SIZE);
        Page<Rating> ratingPage = ratingService.findAll(pageable);

        assertEquals(RATING_PAGE_SIZE, ratingPage.getNumberOfElements());

    }

    @Test
    void testFindById() {
        Optional<Rating> existingRating = ratingService.findById(EXISTING_RATING_ID);

        assertTrue(existingRating.isPresent());
        assertEquals(EXISTING_RATING_ID, existingRating.get().getId());
    }

    @Test
    void testCreate() throws Exception {
        Rating newRating = new Rating(NEW_RATING_VALUE, NEW_RATING_COMMENT);
        Rating createdRating = ratingService.create(newRating);

        assertNotNull(createdRating);
        assertEquals(NEW_RATING_ID, createdRating.getId());

    }

    @Test
    void testUpdate() throws Exception {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setComment(EXISTING_RATING_COMMENT_UPDATE);
        ratingDTO.setRatingValue(EXISTING_RATING_VALUE_UPDATE);

        Rating updatedRating = ratingService.update(EXISTING_RATING_ID, ratingDTO);

        assertEquals(EXISTING_RATING_COMMENT_UPDATE, updatedRating.getComment());
        assertEquals(EXISTING_RATING_VALUE_UPDATE, updatedRating.getRatingValue());


    }

    @Test
    void testUpdateComment() throws Exception {

        Rating updatedRating = ratingService.updateComment(EXISTING_RATING_ID, EXISTING_RATING_COMMENT_UPDATE2);
        assertEquals(EXISTING_RATING_COMMENT_UPDATE2, updatedRating.getComment());

    }

    @Test
    void testDelete() {

    }
}