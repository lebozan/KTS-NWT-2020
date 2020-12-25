package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.PageDTO;
import com.ktsnwt.Culturalcontentapp.dto.RegisterDTO;
import com.ktsnwt.Culturalcontentapp.dto.SubscriptionsDTO;
import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.helper.SubscriptionsMapper;
import com.ktsnwt.Culturalcontentapp.helper.UserMapper;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.model.RegisteredUser;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferService;
import com.ktsnwt.Culturalcontentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CulturalOfferService culturalOfferService;

    private final UserMapper userMapper;
    private final SubscriptionsMapper subscriptionsMapper;

    public UserController() {
        userMapper = new UserMapper();
        subscriptionsMapper = new SubscriptionsMapper();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.findAllUsers(pageable);

        Page<UserDTO> userDTOS = users.map(userMapper::toDto);

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toDto(user.get()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping(value = "/{id}/subscriptions", method = RequestMethod.GET)
    public ResponseEntity<SubscriptionsDTO> getUserSubscriptions(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RegisteredUser registeredUser = (RegisteredUser) user.get();
        return new ResponseEntity<>(subscriptionsMapper.toDto(registeredUser.getSubscriptions()), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        User updatedUser;
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            updatedUser = userService.update(optionalUser.get().getId(), userDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userMapper.toDto(updatedUser), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);

        try {
            userService.delete(optionalUser.orElseThrow().getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/subscribe/{culturalOfferId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> addSubscription(@PathVariable Long culturalOfferId) {
//        Optional<User> optionalUser = userService.findById(userId);
        Optional<CulturalOffer> optionalCulturalOffer = culturalOfferService.findOne(culturalOfferId);
        if (optionalCulturalOffer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        RegisteredUser user = (RegisteredUser) optionalUser.get();
        CulturalOffer culturalOffer = optionalCulturalOffer.get();

//        user.getSubscriptions().add(culturalOffer);

        userService.addSubscription(culturalOffer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/unsubscribe/{culturalOfferId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> removeSubscription(@PathVariable Long culturalOfferId) throws Exception {

        Optional<CulturalOffer> optionalCulturalOffer = culturalOfferService.findOne(culturalOfferId);
        if (optionalCulturalOffer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CulturalOffer culturalOffer = optionalCulturalOffer.get();

        userService.removeSubscription(culturalOffer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
