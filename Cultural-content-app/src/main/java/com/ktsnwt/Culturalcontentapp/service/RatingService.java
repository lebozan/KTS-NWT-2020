package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.dto.RatingDTO;
import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.model.Rating;
import com.ktsnwt.Culturalcontentapp.model.Role;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Page<Rating> findAll(Pageable pageable) {
        return ratingRepository.findAll(pageable);
    }


    public Optional<Rating> findById(Long id) {
        return ratingRepository.findById(id);
    }


    public Rating create(Rating newRating) throws Exception {
//        if (ratingRepository.findById(newRating.getId()).isPresent()) {
//            throw new Exception("Rating with given id already exists!");
//        }

        return ratingRepository.save(newRating);
    }

    public Rating update(Long id, RatingDTO updateDTO) throws Exception {
        Optional<Rating> existingRating = ratingRepository.findById(id);

        if (existingRating.isEmpty()) {
            throw new Exception("Rating with given id doesn't exist!");
        }
        Rating rating = existingRating.get();

        if (rating.getComment() != null) {
            rating.setComment(updateDTO.getComment());
        }

        if (rating.getRatingValue() != null) {
            rating.setRatingValue(updateDTO.getRatingValue());
        }



        return ratingRepository.save(rating);
    }

    public Rating updateComment(Long id, String newComment) throws Exception{
        Optional<Rating> existingRating = ratingRepository.findById(id);
        if (existingRating.isEmpty()) {
            throw new Exception("Rating with given id doesn't exist!");
        }

        Rating rating = existingRating.get();
        rating.setComment(newComment);

        return ratingRepository.save(rating);
    }


    public void delete(Long id) throws Exception {
        Optional<Rating> existingRating = ratingRepository.findById(id);
        if (existingRating.isEmpty()) {
            throw new Exception("Rating with given id doesn't exist!");
        }

        ratingRepository.delete(existingRating.get());
    }
}
