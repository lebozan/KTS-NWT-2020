package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.PageDTO;
import com.ktsnwt.Culturalcontentapp.dto.RatingDTO;
import com.ktsnwt.Culturalcontentapp.helper.RatingMapper;
import com.ktsnwt.Culturalcontentapp.model.Rating;
import com.ktsnwt.Culturalcontentapp.model.User;
import com.ktsnwt.Culturalcontentapp.service.RatingService;
import com.ktsnwt.Culturalcontentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RatingDTO>> getAllRatings(@RequestBody @Validated PageDTO pageDTO) {
        Page<Rating> ratings = ratingService.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
        List<RatingDTO> ratingDTOS = new ArrayList<>();
        for (Rating r : ratings) {
            ratingDTOS.add(ratingMapper.toDto(r));
        }

        return new ResponseEntity<>(ratingDTOS, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RatingDTO> getRating(@PathVariable Long id) {
        Optional<Rating> rating = ratingService.findById(id);
        if (rating.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ratingMapper.toDto(rating.get()), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingDTO> createRating(@RequestBody @Valid RatingDTO ratingDTO) {
        Rating newRating;
        try {
            Optional<User> u = userService.findByEmail(ratingDTO.getUser().getEmail());
            newRating = ratingMapper.toEntity(ratingDTO);
            newRating.setUser(u.orElseThrow());
            newRating = ratingService.create(newRating);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ratingMapper.toDto(newRating), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RatingDTO> updateRating(@RequestBody @Validated RatingDTO ratingDTO, @PathVariable Long id) {
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


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        Optional<Rating> optionalRating = ratingService.findById(id);

        try {
            ratingService.delete(optionalRating.orElseThrow().getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
