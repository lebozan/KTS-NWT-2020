package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.*;
import com.ktsnwt.Culturalcontentapp.helper.UserMapper;
import com.ktsnwt.Culturalcontentapp.model.Authority;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.security.AuthTokenUtils;
import com.ktsnwt.Culturalcontentapp.service.AuthorityService;
import com.ktsnwt.Culturalcontentapp.service.CustomUserDetailService;
import com.ktsnwt.Culturalcontentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {


    @Autowired
    private AuthTokenUtils tokenUtils;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    private final UserMapper userMapper;

    public AuthController() {
        userMapper = new UserMapper();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAndCreateToken(@RequestBody LoginDTO loginDTO) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new AuthTokenDTO(jwt, expiresIn));
    }

    // Endpoint za registraciju novog korisnika
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody RegisterDTO registerRequest) throws Exception {
        User newUser;
        Optional<User> exists = this.userService.findByEmail(registerRequest.getEmail());

        if (exists.isPresent()) {
            throw new Exception("Email already in use");
        }

        try {
            newUser = userMapper.toRegisterEntity(registerRequest);
            List<Authority> authorities = authorityService.findByName("ROLE_USER");
            newUser.setAuthorities(authorities);
            newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            newUser = userService.create(newUser);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userMapper.toDto(newUser), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        userDetailService.changePassword(passwordChangeDTO.getOldPassword(), passwordChangeDTO.getNewPassword());

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        result.put("message", "Password changed successfully!");
        return ResponseEntity.accepted().body(result);
    }

}
