package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.model.RegisteredUser;
import com.ktsnwt.Culturalcontentapp.model.User;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.ktsnwt.Culturalcontentapp.constants.UserConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class UserServiceIntegrationTest {

    @Autowired
    UserService userService;

    @Autowired
    CulturalOfferService culturalOfferService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Test
    void findAllUsers() {
        Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
        Page<User> foundUserPage = userService.findAllUsers(pageable);

        assertEquals(FIND_ALL_USERS_NUMBER_OF_USERS, foundUserPage.getNumberOfElements());
    }

    @Test
    void findById() {
        Optional<User> findUser = userService.findById(USER_ID1);
        assertTrue(findUser.isPresent());
        assertEquals(USER_ID1, findUser.get().getId());
    }

    @Test
    void findByEmail() {
        Optional<User> findUser = userService.findByEmail(USER_EMAIL1);
        assertTrue(findUser.isPresent());
        assertEquals(USER_EMAIL1, findUser.get().getEmail());

    }

    @Test
    void create() throws Exception {
        User newUser = new User(NEW_USER_FIRSTNAME, NEW_USER_LASTNAME, NEW_USER_EMAIL, NEW_USER_PASSWORD);
        User savedUser = userService.create(newUser);

        assertEquals(NEW_USER_EMAIL, savedUser.getEmail());
    }

    @Test
    void update() throws Exception {
        UserDTO updateDTO = new UserDTO(UPDATE_USER_ID, UPDATE_USER_FIRSTNAME, UPDATE_USER_LASTNAME, UPDATE_USER_EMAIL);
        User updatedUser = userService.update(UPDATE_USER_ID, updateDTO);

        assertEquals(UPDATE_USER_EMAIL, updatedUser.getEmail());
        assertEquals(UPDATE_USER_FIRSTNAME, updatedUser.getFirstName());
        assertEquals(UPDATE_USER_LASTNAME, updatedUser.getLastName());
    }

    @Test
    void addSubscription() {

//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(USER_EMAIL2, USER_PASSWORD2));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//        CulturalOffer newSubscription = culturalOfferService.findByName(ADD_SUBSCRIPTION_NAME);
//        userService.addSubscription(USER_ID2, newSubscription);
//
//
//        RegisteredUser updatedUser = (RegisteredUser) userService.findById(USER_ID2).orElseThrow();
//        assertEquals(NUMBER_OF_SUBSCRIPTIONS, updatedUser.getSubscriptions().size());


    }

    @Test
    void removeSubscription() {

    }

    @Test
    void delete() {

    }
}