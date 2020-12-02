package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;
import com.ktsnwt.Culturalcontentapp.model.Role;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.repository.CulturalOfferTypeRepository;
import com.ktsnwt.Culturalcontentapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllUsers() {
        return userRepository.findAllByRole(Role.REGISTERED_USER);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(User newUser) throws Exception {
        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            throw new Exception("User with given email already exists!");
        }

        return userRepository.save(newUser);
    }

    public void update(Long id, UserDTO updateDTO) throws Exception {
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
        if (updateDTO.getPassword() != null) {
            user.setPassword(updateDTO.getPassword());
        }
        

        userRepository.save(user);
    }


    public void delete(Long id) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new Exception("User with given id doesn't exist!");
        }

        userRepository.delete(existingUser.get());
    }
}
