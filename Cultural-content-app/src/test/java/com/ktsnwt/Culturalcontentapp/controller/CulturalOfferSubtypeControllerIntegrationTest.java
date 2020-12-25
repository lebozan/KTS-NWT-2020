package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.AuthTokenDTO;
import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferSubtypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.LoginDTO;
import com.ktsnwt.Culturalcontentapp.helper.CulturalOfferSubtypePageImpl;
import com.ktsnwt.Culturalcontentapp.helper.CulturalOfferTypePageImpl;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferSubtypeService;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferTypeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferTypeConstants.*;
import static com.ktsnwt.Culturalcontentapp.constants.UserConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferSubtypeConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CulturalOfferSubtypeControllerIntegrationTest {

    @Autowired
    CulturalOfferTypeService culturalOfferTypeService;

    @Autowired
    CulturalOfferSubtypeService culturalOfferSubtypeService;

    private String accessToken;

    @Autowired
    private TestRestTemplate restTemplate;

    public void login(String username, String password) {
        ResponseEntity<AuthTokenDTO> responseEntity = restTemplate.postForEntity("/auth/login",
                new LoginDTO(username,password), AuthTokenDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    void getAllCulturalOfferSubtypes() {

        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<CulturalOfferSubtypePageImpl<CulturalOfferSubtypeDTO>> culturalOfferSubtypePageResponseType =
                new ParameterizedTypeReference<CulturalOfferSubtypePageImpl<CulturalOfferSubtypeDTO>>() {};


        ResponseEntity<CulturalOfferSubtypePageImpl<CulturalOfferSubtypeDTO>> responseEntity =
                restTemplate.exchange("/api/cultural-offer-subtypes?page=0&size=2", HttpMethod.GET, httpEntity, culturalOfferSubtypePageResponseType);

        List<CulturalOfferSubtypeDTO> allCulturalOfferTypes = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(allCulturalOfferTypes);
        assertEquals(SUBTYPE_PAGE_SIZE, allCulturalOfferTypes.size());
    }

    @Test
    void getCulturalOfferSubtype() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<CulturalOfferSubtypeDTO> responseEntity =
                restTemplate.exchange("/api/cultural-offer-subtypes/102", HttpMethod.GET, httpEntity, CulturalOfferSubtypeDTO.class);

        CulturalOfferSubtypeDTO culturalOfferSubtype = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(culturalOfferSubtype);
        assertEquals(EXISTING_SUBTYPE_ID, culturalOfferSubtype.getId());

    }

    @Test
    void createCulturalOfferSubtype() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        CulturalOfferSubtypeDTO newSubtype = new CulturalOfferSubtypeDTO();
        newSubtype.setName(NEW_SUBTYPE_NAME);
        CulturalOfferTypeDTO newSubtypeType = new CulturalOfferTypeDTO();
        newSubtypeType.setName(NEW_SUBTYPE_TYPE_NAME);
        newSubtype.setType(newSubtypeType);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(newSubtype, headers);


        ResponseEntity<CulturalOfferSubtypeDTO> responseEntity =
                restTemplate.exchange("/api/cultural-offer-subtypes", HttpMethod.POST, httpEntity, CulturalOfferSubtypeDTO.class);

        CulturalOfferSubtypeDTO newCulturalOfferSubtype = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(newCulturalOfferSubtype);
        assertEquals(NEW_SUBTYPE_NAME, newCulturalOfferSubtype.getName());


    }

    @Test
    void updateCulturalOfferSubtype() {

        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        CulturalOfferSubtypeDTO updateDTO = new CulturalOfferSubtypeDTO();
        updateDTO.setName(EXISTING_SUBTYPE_NAME_UPDATE);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(updateDTO, headers);


        ResponseEntity<CulturalOfferSubtypeDTO> responseEntity =
                restTemplate.exchange("/api/cultural-offer-subtypes/102", HttpMethod.PUT, httpEntity, CulturalOfferSubtypeDTO.class);

        CulturalOfferSubtypeDTO updatedCulturalOfferSubtype = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(updatedCulturalOfferSubtype);
        assertEquals(EXISTING_SUBTYPE_NAME_UPDATE, updatedCulturalOfferSubtype.getName());

    }

    @Test
    void deleteCulturalOfferSubtype() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<Void> responseEntity =
                restTemplate.exchange("/api/cultural-offer-subtypes/101", HttpMethod.DELETE, httpEntity, Void.class);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}