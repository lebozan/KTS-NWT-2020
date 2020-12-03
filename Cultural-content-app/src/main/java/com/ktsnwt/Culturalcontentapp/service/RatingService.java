package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.RatingDTO;
import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.model.Rating;
import com.ktsnwt.Culturalcontentapp.model.Role;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }


    public Optional<Rating> findById(Long id) {
        return ratingRepository.findById(id);
    }


    public Rating create(Rating newRating) throws Exception {
//        if (ratingRepository.findById(newRating.getId()) != null) {
//            throw new Exception("Rating with given id already exists!");
//        }

        return ratingRepository.save(newRating);
    }

    public void update(Long id, RatingDTO updateDTO) throws Exception {
        Optional<Rating> existingRating = ratingRepository.findById(id);
        if (existingRating.isEmpty()) {
            throw new Exception("User with given id doesn't exist!");
        }
        Rating rating = existingRating.get();

//        if (updateDTO.getEmail() != null) {
//            user.setEmail(updateDTO.getEmail());
//        }
//        if (updateDTO.getFirstName() != null) {
//            user.setFirstName(updateDTO.getFirstName());
//        }
//        if (updateDTO.getLastName() != null ) {
//            user.setLastName(updateDTO.getLastName());
//        }
//        if (updateDTO.getPassword() != null) {
//            user.setPassword(updateDTO.getPassword());
//        }


        ratingRepository.save(rating);
    }


    public void delete(Long id) throws Exception {
        Optional<Rating> existingRating = ratingRepository.findById(id);
        if (existingRating.isEmpty()) {
            throw new Exception("User with given id doesn't exist!");
        }

        ratingRepository.delete(existingRating.get());
    }
}
