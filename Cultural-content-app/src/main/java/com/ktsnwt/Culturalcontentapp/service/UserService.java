package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.model.RegisteredUser;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(User newUser) throws Exception {
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new Exception("User with given email already exists!");
        }

        return userRepository.save(newUser);
    }

    public User update(Long id, UserDTO updateDTO) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new Exception("User with given id doesn't exist!");
        }
        User user = existingUser.get();

        if (updateDTO.getEmail() != null) {
            user.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getFirstName() != null) {
            user.setFirstName(updateDTO.getFirstName());
        }
        if (updateDTO.getLastName() != null ) {
            user.setLastName(updateDTO.getLastName());
        }

        return userRepository.save(user);
    }

    public void addSubscription(CulturalOffer culturalOffer) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) currentUser.getPrincipal();


        Optional<User> user = userRepository.findByEmail(authenticatedUser.getEmail());
        RegisteredUser updateUser = (RegisteredUser) user.orElseThrow();
        updateUser.getSubscriptions().add(culturalOffer);

        userRepository.save(updateUser);
    }

    public void removeSubscription(CulturalOffer culturalOffer) throws Exception {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = ((User) currentUser.getPrincipal()).getEmail();

        Optional<User> user = userRepository.findByEmail(username);
        RegisteredUser updateUser = (RegisteredUser) user.orElseThrow();
        if (updateUser.getSubscriptions().remove(culturalOffer)) {
            userRepository.save(updateUser);
        } else {
            throw new Exception("not removed");
        }


    }

    public void delete(Long id) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new Exception("User with given id doesn't exist!");
        }

        userRepository.delete(existingUser.get());
    }
}
