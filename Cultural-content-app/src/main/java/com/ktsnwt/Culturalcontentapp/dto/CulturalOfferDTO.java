package com.ktsnwt.Culturalcontentapp.dto;

import java.util.Set;

import com.ktsnwt.Culturalcontentapp.model.Image;
import com.ktsnwt.Culturalcontentapp.model.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CulturalOfferDTO {

    private String name;
    private Set<Image> images;
    private Location location;
    private String description;
}
