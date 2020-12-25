package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.AuthTokenDTO;
import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.LoginDTO;
import com.ktsnwt.Culturalcontentapp.dto.RatingDTO;
import com.ktsnwt.Culturalcontentapp.helper.CulturalOfferTypePageImpl;
import com.ktsnwt.Culturalcontentapp.helper.RatingPageImpl;
import com.ktsnwt.Culturalcontentapp.model.Rating;
import com.ktsnwt.Culturalcontentapp.service.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferTypeConstants.TYPE_PAGE_SIZE;
import static org.junit.jupiter.api.Assertions.*;

import static com.ktsnwt.Culturalcontentapp.constants.UserConstants.*;
import static com.ktsnwt.Culturalcontentapp.constants.RatingConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class RatingControllerIntegrationTest {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private TestRestTemplate restTemplate;

    private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<AuthTokenDTO> responseEntity = restTemplate.postForEntity("/auth/login",
                new LoginDTO(username,password), AuthTokenDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    void testGetAllRatings() {

        login(USER_EMAIL2, USER_PASSWORD2);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ParameterizedTypeReference<RatingPageImpl<RatingDTO>> ratingPageResponseType =
                new ParameterizedTypeReference<>() {};


        ResponseEntity<RatingPageImpl<RatingDTO>> responseEntity =
                restTemplate.exchange("/api/ratings?page=0&size=2", HttpMethod.GET, httpEntity, ratingPageResponseType);

        List<RatingDTO> ratingsPage = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(ratingsPage);
        assertEquals(RATING_PAGE_SIZE, ratingsPage.size());

    }

    @Test
    void testGetRating() {

        login(USER_EMAIL2, USER_PASSWORD2);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<RatingDTO> responseEntity =
                restTemplate.exchange("/api/ratings/100", HttpMethod.GET, httpEntity, RatingDTO.class);

        RatingDTO rating = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(rating);
        assertEquals(EXISTING_RATING_ID, rating.getId());

    }

    @Test
    @Rollback
    void testCreateRating() {
        login(USER_EMAIL2, USER_PASSWORD2);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        RatingDTO newRating = new RatingDTO();
        newRating.setComment(NEW_RATING_COMMENT);
        newRating.setRatingValue(NEW_RATING_VALUE);
        HttpEntity<Object> httpEntity = new HttpEntity<>(newRating, headers);

        ResponseEntity<RatingDTO> responseEntity =
                restTemplate.exchange("/api/ratings", HttpMethod.POST, httpEntity, RatingDTO.class);

        RatingDTO rating = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(rating);
        assertEquals(NEW_RATING_ID, rating.getId());
        assertEquals(NEW_RATING_COMMENT, rating.getComment());
        assertEquals(NEW_RATING_VALUE, rating.getRatingValue());
    }

    @Test
    void testUpdateRating() {

        login(USER_EMAIL2, USER_PASSWORD2);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        RatingDTO updateDTO = new RatingDTO();
        updateDTO.setComment(EXISTING_RATING_COMMENT_UPDATE2);
        updateDTO.setRatingValue(EXISTING_RATING_VALUE_UPDATE);
        HttpEntity<Object> httpEntity = new HttpEntity<>(updateDTO, headers);

        ResponseEntity<RatingDTO> responseEntity =
                restTemplate.exchange("/api/ratings/100", HttpMethod.PUT, httpEntity, RatingDTO.class);

        RatingDTO updatedRating = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(updatedRating);
        assertEquals(EXISTING_RATING_ID, updatedRating.getId());
        assertEquals(EXISTING_RATING_COMMENT_UPDATE2, updatedRating.getComment());
        assertEquals(EXISTING_RATING_VALUE_UPDATE, updatedRating.getRatingValue());


    }

    @Test
    void testUpdateComment() {

        login(USER_EMAIL2, USER_PASSWORD2);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        RatingDTO updateDTO = new RatingDTO();
        updateDTO.setComment(EXISTING_RATING_COMMENT_UPDATE2);
        updateDTO.setRatingValue(EXISTING_RATING_VALUE_UPDATE);
        HttpEntity<Object> httpEntity = new HttpEntity<>(updateDTO, headers);

        ResponseEntity<RatingDTO> responseEntity =
                restTemplate.exchange("/api/ratings/100/comment", HttpMethod.PUT, httpEntity, RatingDTO.class);

        RatingDTO updatedRating = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(updatedRating);
        assertEquals(EXISTING_RATING_ID, updatedRating.getId());
        assertEquals(EXISTING_RATING_COMMENT_UPDATE2, updatedRating.getComment());

    }

    @Test
    void testDeleteRating() {

        login(USER_EMAIL2, USER_PASSWORD2);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> responseEntity =
                restTemplate.exchange("/api/ratings/102", HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}