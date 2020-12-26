package com.ktsnwt.Culturalcontentapp.dto;

import java.util.Set;

import com.ktsnwt.Culturalcontentapp.model.Image;
import com.ktsnwt.Culturalcontentapp.model.Location;
import com.ktsnwt.Culturalcontentapp.model.News;
import com.ktsnwt.Culturalcontentapp.model.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CulturalOfferDTO {

    private long id;
    private String name;
    private Set<Image> images;
    private String location;
    private String description;
    private CulturalOfferSubtypeDTO subtype;
    //proveri da li je rating i news ili ratingDTO i newsDTO
    private Set<Rating> ratings;
    private Set<News> news;
}
