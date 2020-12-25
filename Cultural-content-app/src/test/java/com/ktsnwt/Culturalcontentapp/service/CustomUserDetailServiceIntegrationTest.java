package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.repository.UserRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.ktsnwt.Culturalcontentapp.constants.UserConstants.*;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class CustomUserDetailServiceIntegrationTest {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Test
    void loadUserByUsername() {

        User user = (User) customUserDetailService.loadUserByUsername(USER_EMAIL1);
        assertEquals(USER_EMAIL1, user.getEmail());

    }

    @Test
    void loadUserByUsernameNonExisting() {

        assertThrows(UsernameNotFoundException.class, () -> {customUserDetailService.loadUserByUsername(NON_EXISTENT_EMAIL);});

    }

    @Test
    void changePassword() {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(USER_EMAIL1, USER_PASSWORD1));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        assertDoesNotThrow(() -> {customUserDetailService.changePassword(USER_PASSWORD1, USER1_PASSWORD_CHANGE);});

    }
}