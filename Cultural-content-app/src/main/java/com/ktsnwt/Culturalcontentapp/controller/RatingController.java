package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.RatingDTO;
import com.ktsnwt.Culturalcontentapp.helper.RatingMapper;
import com.ktsnwt.Culturalcontentapp.model.Rating;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.service.RatingService;
import com.ktsnwt.Culturalcontentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Autowired
    UserService userService;

    private final RatingMapper ratingMapper;

    public RatingController() {
        ratingMapper = new RatingMapper();
    }


    // http://localhost:8080/api/ratings
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RatingDTO>> getAllRatings() {
        List<Rating> ratings = ratingService.findAll();
        List<RatingDTO> ratingDTOS = new ArrayList<>();
        for (Rating r : ratings) {
            ratingDTOS.add(ratingMapper.toDto(r));
        }

        return new ResponseEntity<>(ratingDTOS, HttpStatus.OK);
    }

    // http://localhost:8080/api/ratings/101
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RatingDTO> getRating(@PathVariable Long id) {
        Optional<Rating> rating = ratingService.findById(id);
        if (rating.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ratingMapper.toDto(rating.get()), HttpStatus.OK);
    }

    // http://localhost:8080/api/ratings/create
    //{
    //    "ratingValue": 1,
    //    "comment": "Jako los komentar",
    //        "user": {
    //        "firstName": "Boban",
    //        "lastName": "Cakic",
    //        "email": "user1@mail.com",
    //        "password": "sifra",
    //        "role": "REGISTERED_USER"
    //    }
    //}
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO) {
        Rating newRating;
        try {
            User u = userService.findByEmail(ratingDTO.getUser().getEmail());
            newRating = ratingMapper.toEntity(ratingDTO);
            newRating.setUser(u);
            newRating = ratingService.create(newRating);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ratingMapper.toDto(newRating), HttpStatus.OK);
    }

    // http://localhost:8080/api/ratings/101
    // body: {
    //    "ratingValue": 4,
    //    "comment": "Jako dobar komentar"
    //}
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RatingDTO> updateRating(@RequestBody RatingDTO ratingDTO, @PathVariable Long id) {
        Optional<Rating> optionalRating = ratingService.findById(id);
        if (optionalRating.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            ratingService.update(optionalRating.get().getId(), ratingDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:8080/api/ratings/102
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        Optional<Rating> optionalRating = ratingService.findById(id);

        try {
            ratingService.delete(optionalRating.get().getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
