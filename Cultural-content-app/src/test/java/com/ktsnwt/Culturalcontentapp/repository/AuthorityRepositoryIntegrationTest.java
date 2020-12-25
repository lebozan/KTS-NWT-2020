package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.Authority;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class AuthorityRepositoryIntegrationTest {

    @Autowired
    AuthorityRepository authorityRepository;

    @Test
    void testFindByName() {
        Authority authority = authorityRepository.findByName("ROLE_USER");
        assertEquals("ROLE_USER", authority.getName());
    }
}