package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.AuthTokenDTO;
import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.LoginDTO;
import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.helper.CulturalOfferTypePageImpl;
import com.ktsnwt.Culturalcontentapp.helper.UserPageImpl;
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

import static com.ktsnwt.Culturalcontentapp.constants.UserConstants.*;
import static com.ktsnwt.Culturalcontentapp.constants.CulturalOfferTypeConstants.*;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CulturalOfferTypeControllerIntegrationTest {

    @Autowired
    CulturalOfferTypeService culturalOfferTypeService;

    private String accessToken;

    @Autowired
    private TestRestTemplate restTemplate;

    public void login(String username, String password) {
        ResponseEntity<AuthTokenDTO> responseEntity = restTemplate.postForEntity("/auth/login",
                new LoginDTO(username,password), AuthTokenDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }


    @Test
    void testGetAllCulturalOfferTypes() {

        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<CulturalOfferTypePageImpl<CulturalOfferTypeDTO>> culturalOfferTypePageResponseType =
                new ParameterizedTypeReference<CulturalOfferTypePageImpl<CulturalOfferTypeDTO>>() {};


        ResponseEntity<CulturalOfferTypePageImpl<CulturalOfferTypeDTO>> responseEntity =
                restTemplate.exchange("/api/cultural-offer-types?page=0&size=2", HttpMethod.GET, httpEntity, culturalOfferTypePageResponseType);

        List<CulturalOfferTypeDTO> allCulturalOfferTypes = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(allCulturalOfferTypes);
        assertEquals(TYPE_PAGE_SIZE, allCulturalOfferTypes.size());

    }

    @Test
    void testGetCulturalOfferType() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<CulturalOfferTypeDTO> responseEntity =
                restTemplate.exchange("/api/cultural-offer-types/101", HttpMethod.GET, httpEntity, CulturalOfferTypeDTO.class);

        CulturalOfferTypeDTO culturalOfferType = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(culturalOfferType);
        assertEquals(EXISTING_TYPE_ID, culturalOfferType.getId());

    }

    @Test
    void testCreateCulturalOfferType() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        CulturalOfferTypeDTO newType = new CulturalOfferTypeDTO();
        newType.setName(NEW_TYPE_NAME);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(newType, headers);


        ResponseEntity<CulturalOfferTypeDTO> responseEntity =
                restTemplate.exchange("/api/cultural-offer-types", HttpMethod.POST, httpEntity, CulturalOfferTypeDTO.class);

        CulturalOfferTypeDTO culturalOfferType = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(culturalOfferType);
        assertEquals(NEW_TYPE_NAME, culturalOfferType.getName());

    }

    @Test
    void testUpdateCulturalOfferType() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        CulturalOfferTypeDTO updateDTO = new CulturalOfferTypeDTO();
        updateDTO.setName(EXISTING_TYPE_NAME_UPDATE);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(updateDTO, headers);


        ResponseEntity<CulturalOfferTypeDTO> responseEntity =
                restTemplate.exchange("/api/cultural-offer-types/101", HttpMethod.PUT, httpEntity, CulturalOfferTypeDTO.class);

        CulturalOfferTypeDTO updatedCulturalOfferType = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(updatedCulturalOfferType);
        assertEquals(EXISTING_TYPE_NAME_UPDATE, updatedCulturalOfferType.getName());

    }

    @Test
    void testDeleteCulturalOfferType() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<Void> responseEntity =
                restTemplate.exchange("/api/cultural-offer-types/102", HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}