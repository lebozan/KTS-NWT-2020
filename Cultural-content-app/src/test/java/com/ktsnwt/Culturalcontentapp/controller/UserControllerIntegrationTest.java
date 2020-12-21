package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.AuthTokenDTO;
import com.ktsnwt.Culturalcontentapp.dto.LoginDTO;
import com.ktsnwt.Culturalcontentapp.dto.SubscriptionsDTO;
import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.helper.UserPageImpl;
import com.ktsnwt.Culturalcontentapp.service.UserService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    UserService userService;

    private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<AuthTokenDTO> responseEntity = restTemplate.postForEntity("/auth/login",
                new LoginDTO(username,password), AuthTokenDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }



    @Test
    void testGetAllUsers() {

        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<UserPageImpl<UserDTO>> userPageResponseType = new ParameterizedTypeReference<UserPageImpl<UserDTO>>() {};


        ResponseEntity<UserPageImpl<UserDTO>> responseEntity = restTemplate.exchange("/api/users?page=0&size=2", HttpMethod.GET, httpEntity, userPageResponseType);

        List<UserDTO> allUsers = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(allUsers);
        assertEquals(PAGE_SIZE, allUsers.size());


    }

    @Test
    void testGetUser() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange("/api/users/1001", HttpMethod.GET, httpEntity, UserDTO.class);

        UserDTO user = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(user);
        assertEquals(USER_ID2, user.getId());

    }

    @Test
    void testGetUserSubscriptions() {

        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<SubscriptionsDTO> responseEntity = restTemplate.exchange("/api/users/1001/subscriptions", HttpMethod.GET, httpEntity, SubscriptionsDTO.class);

        SubscriptionsDTO subs = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(subs);
        assertEquals(USER2_NUMBER_OF_SUBSCRIPTIONS, subs.getSubscriptions().size());

    }

    @Test
    void testUpdateUser() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        UserDTO updateDTO = new UserDTO();
        updateDTO.setEmail(UPDATE_USER_EMAIL);
        updateDTO.setFirstName(UPDATE_USER_FIRSTNAME);
        updateDTO.setLastName(UPDATE_USER_LASTNAME);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(updateDTO, headers);


        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange("/api/users/1001", HttpMethod.PUT, httpEntity, UserDTO.class);

        UserDTO updatedUser = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(updatedUser);
        assertEquals(UPDATE_USER_EMAIL, updatedUser.getEmail());
        assertEquals(UPDATE_USER_FIRSTNAME, updatedUser.getFirstName());
        assertEquals(UPDATE_USER_LASTNAME, updatedUser.getLastName());


    }

    @Test
    void testDeleteUser() {
        login(ADMIN_EMAIL1, ADMIN_PASSWORD1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/users/1004", HttpMethod.DELETE, httpEntity, Void.class);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void testAddSubscription() {

        login(USER_EMAIL2, USER_PASSWORD2);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/users/subscribe/102", HttpMethod.PUT, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void testRemoveSubscription() {

        login(USER_EMAIL1, USER_PASSWORD1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/users/unsubscribe/100", HttpMethod.PUT, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}