package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.User;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.ktsnwt.Culturalcontentapp.constants.UserConstants.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
class UserRepositoryIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByEmail() {
        Optional<User> findUser = userRepository.findByEmail(ADMIN_EMAIL1);

        assertTrue(findUser.isPresent());
        assertEquals(ADMIN_EMAIL1, findUser.get().getEmail());
    }

    @Test
    void findByFirstName() {
        User findUser = userRepository.findByFirstName(ADMIN_FIRSTNAME1);

        assertNotNull(findUser);
        assertEquals(ADMIN_FIRSTNAME1, findUser.getFirstName());

    }

    @Test
    void findByLastName() {
        User findUser = userRepository.findByLastName(ADMIN_LASTNAME1);

        assertNotNull(findUser);
        assertEquals(ADMIN_LASTNAME1, findUser.getLastName());
    }

    @Test
    void findByFirstNameAndLastName() {
        User findUser = userRepository.findByFirstNameAndLastName(ADMIN_FIRSTNAME1, ADMIN_LASTNAME1);

        assertNotNull(findUser);
        assertEquals(ADMIN_FIRSTNAME1, findUser.getFirstName());
        assertEquals(ADMIN_LASTNAME1, findUser.getLastName());
    }
}