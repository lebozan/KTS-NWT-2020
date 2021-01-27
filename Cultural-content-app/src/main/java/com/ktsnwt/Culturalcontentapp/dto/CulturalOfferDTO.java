package com.ktsnwt.Culturalcontentapp.dto;

import java.util.List;
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
    private Location location;
    private String description;
    private CulturalOfferSubtypeDTO subtype;
    //proveri da li je rating i news ili ratingDTO i newsDTO
    private List<RatingDTO> ratings;
//    private Set<News> news;
}
