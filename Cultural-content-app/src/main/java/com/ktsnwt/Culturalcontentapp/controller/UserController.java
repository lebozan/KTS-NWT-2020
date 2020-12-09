package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.PageDTO;
import com.ktsnwt.Culturalcontentapp.dto.RegisterDTO;
import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.helper.UserMapper;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    private final UserMapper userMapper;

    public UserController() {
        userMapper = new UserMapper();
    }

    // http://localhost:8080/api/users
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestBody PageDTO pageDTO) {
        Page<User> users = userService.findAllUsers(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));

        Page<UserDTO> userDTOS = users.map(userMapper::toDto);

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    // http://localhost:8080/api/users/1001
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toDto(user.get()), HttpStatus.OK);
    }


//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid RegisterDTO newUserDTO) {
//        User newUser;
//        try {
//            newUser = userMapper.toRegisterEntity(newUserDTO);
//            newUser = userService.create(newUser);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(userMapper.toDto(newUser), HttpStatus.OK);
//    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.update(optionalUser.get().getId(), userDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:8080/api/users/1
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

}
