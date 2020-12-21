package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.repository.UserRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ktsnwt.Culturalcontentapp.constants.UserConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class UserServiceUnitTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    void setUp() {

        List<User> users = new ArrayList<>();
        users.add(new User(USER_FIRSTNAME1, USER_LASTNAME1, USER_EMAIL1, USER_PASSWORD1));
        users.add(new User(USER_FIRSTNAME2, USER_LASTNAME2, USER_EMAIL2, USER_PASSWORD2));

        BDDMockito.given(userRepository.findAll()).willReturn(users);

        Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
        Page<User> usersPage = new PageImpl<>(users, pageable, 2);

        BDDMockito.given(userRepository.findAll(pageable)).willReturn(usersPage);

        User existingUser = new User(USER_ID1, USER_FIRSTNAME1, USER_LASTNAME1, USER_EMAIL1);

        User newUser = new User(NEW_USER_FIRSTNAME, NEW_USER_LASTNAME, NEW_USER_EMAIL, NEW_USER_PASSWORD);
        User savedUser = new User(NEW_USER_FIRSTNAME, NEW_USER_LASTNAME, NEW_USER_EMAIL, NEW_USER_PASSWORD);
        Optional<User> nonExistingUser = Optional.empty();

        BDDMockito.given(userRepository.findById(USER_ID1)).willReturn(Optional.of(existingUser));

        BDDMockito.given(userRepository.findByEmail(USER_EMAIL1)).willReturn(Optional.of(existingUser));

        BDDMockito.given(userRepository.findByEmail(NEW_USER_EMAIL)).willReturn(nonExistingUser);
        BDDMockito.given(userRepository.save(newUser)).willReturn(savedUser);

        User updatedUser = new User(USER_ID1, USER_FIRSTNAME1, USER_LASTNAME1, USER_EMAIL1);

        updatedUser.setId(UPDATE_USER_ID);
        BDDMockito.given(userRepository.findById(UPDATE_USER_ID)).willReturn(Optional.of(updatedUser));


        updatedUser.setEmail(UPDATE_USER_EMAIL);
        updatedUser.setFirstName(UPDATE_USER_FIRSTNAME);
        updatedUser.setLastName(UPDATE_USER_LASTNAME);

        BDDMockito.given(userRepository.save(updatedUser)).willReturn(updatedUser);

    }

    @Test
    void testFindAll() {
        List<User> users = userService.findAll();

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        assertEquals(FIND_ALL_NUMBER_OF_USERS, users.size());

    }

    @Test
    void testFindAllUsers() {
        Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
        Page<User> foundPage = userService.findAllUsers(pageable);

        Mockito.verify(userRepository, Mockito.times(1)).findAll(pageable);
        assertEquals(FIND_ALL_USERS_NUMBER_OF_USERS, foundPage.getNumberOfElements());

    }

    @Test
    void testFindById() {
        Optional<User> user = userService.findById(USER_ID1);

        Mockito.verify(userRepository, Mockito.times(1)).findById(USER_ID1);
        assertEquals(USER_ID1, user.orElseThrow().getId());

    }

    @Test
    void testFindByEmail() {
        Optional<User> user = userService.findByEmail(USER_EMAIL1);

        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(USER_EMAIL1);
        assertEquals(USER_EMAIL1, user.orElseThrow().getEmail());
    }

    @Test
    void testCreate() throws Exception {
        User newUser = new User(NEW_USER_FIRSTNAME, NEW_USER_LASTNAME, NEW_USER_EMAIL, NEW_USER_PASSWORD);
        User createdUser = userService.create(newUser);

        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(NEW_USER_EMAIL);
        Mockito.verify(userRepository, Mockito.times(1)).save(newUser);

        assertEquals(NEW_USER_EMAIL, createdUser.getEmail());

    }

    @Test
    void testCreate_EmailExists() {
        User newUser = new User(USER_ID1, USER_FIRSTNAME1, USER_LASTNAME1, USER_EMAIL1);
        User createdUser = null;
        try {
            createdUser = userService.create(newUser);
        } catch (Exception ignored) {

        }

        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(USER_EMAIL1);


        assertNull(createdUser);

    }

    @Test
    void testUpdate() throws Exception {
        UserDTO updateDTO = new UserDTO(UPDATE_USER_ID, UPDATE_USER_FIRSTNAME, UPDATE_USER_LASTNAME, UPDATE_USER_EMAIL);
        User updatedUser = userService.update(UPDATE_USER_ID, updateDTO);

        Mockito.verify(userRepository, Mockito.times(1)).findById(UPDATE_USER_ID);

        assertEquals("promena ime", updatedUser.getFirstName());
        assertEquals("promena prezime", updatedUser.getLastName());
        assertEquals("promena@mail.com", updatedUser.getEmail());

    }

    @Test
    void testAddSubscription() {

    }

    @Test
    void testRemoveSubscription() {

    }

    @Test
    void testDelete() {

    }
}