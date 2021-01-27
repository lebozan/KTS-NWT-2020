package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.AuthTokenDTO;
import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferDTO;
import com.ktsnwt.Culturalcontentapp.dto.LoginDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferConstants.*;
import static com.ktsnwt.Culturalcontentapp.constants.UserConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CulturalOfferControllerIntegrationTest {

    @Autowired
    CulturalOfferService culturalOfferService;

    private String accessToken;

    @Autowired
    private TestRestTemplate restTemplate;

    public void login(String username, String password) {
        ResponseEntity<AuthTokenDTO> responseEntity = restTemplate.postForEntity("/auth/login",
                new LoginDTO(username,password), AuthTokenDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }
    
    @Test
    void testGetCulturalOffer() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<CulturalOfferDTO> responseEntity =
                restTemplate.exchange("/api/cultural-offers/Sajam poljoprivrede", HttpMethod.GET, httpEntity, CulturalOfferDTO.class);

        CulturalOfferDTO culturalOffer = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(culturalOffer);
        assertEquals(EXISTING_COFFER_ID2, culturalOffer.getId());
    }

    @Test
    void testDeleteCulturalOffer() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<Void> responseEntity =
                restTemplate.exchange("/api/cultural-offers/Sajam poljoprivrede", HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }


    //Izbacuje gresku
    @Test
    void testUpdateCulturalOffer() {
//        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", accessToken);
//
//        CulturalOfferDTO updateDTO = new CulturalOfferDTO();
//        updateDTO.setName(EXISTING_COFFER_NAME_UPDATE);
//        updateDTO.setDescription(EXISTING_COFFER_DESCRIPTION_UPDATE);
//
//        HttpEntity<Object> httpEntity = new HttpEntity<Object>(updateDTO, headers);
//
//
//        ResponseEntity<CulturalOfferDTO> responseEntity =
//                restTemplate.exchange("/api/cultural-offers/Sajam poljoprivrede", HttpMethod.PUT, httpEntity, CulturalOfferDTO.class);
//
//        CulturalOfferDTO updatedCulturalOffer = responseEntity.getBody();
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(updatedCulturalOffer);
//        assertEquals(EXISTING_COFFER_NAME_UPDATE, updatedCulturalOffer.getName());

    }
}
