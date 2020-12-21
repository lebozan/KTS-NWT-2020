package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.ktsnwt.Culturalcontentapp.constants.UserConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest(properties = "classpath:application-test.properties")
@TestPropertySource("classpath:application-test.properties")
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Before
    public void addTestUser() {
        entityManager.persist(new User(ADMIN_EMAIL1, ADMIN_FIRSTNAME1, NEW_USER_EMAIL, NEW_USER_PASSWORD));
    }

    @Test
    void findByEmail() {
        Optional<User> user = userRepository.findByEmail(ADMIN_EMAIL1);
        assertTrue(user.isPresent());
    }

    @Test
    void findByFirstName() {
        User user = userRepository.findByFirstName(ADMIN_FIRSTNAME1);
        assertEquals("Bojan", user.getFirstName());
    }

    @Test
    void findByLastName() {
        User user = userRepository.findByLastName(ADMIN_LASTNAME1);
        assertEquals("Cakic", user.getLastName());
    }

    @Test
    void findByFirstNameAndLastName() {
        User user = userRepository.findByFirstNameAndLastName(ADMIN_FIRSTNAME1, ADMIN_LASTNAME1);
        assertEquals("Bojan Cakic", user.getFirstName() + " " + user.getLastName());
    }
}