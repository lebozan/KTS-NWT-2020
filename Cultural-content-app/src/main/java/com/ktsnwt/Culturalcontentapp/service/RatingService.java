package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

}
