package com.ktsnwt.Culturalcontentapp.dto;

import java.util.Date;
import java.util.Set;

import com.ktsnwt.Culturalcontentapp.model.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsDTO {
    private String title;
    private String text;
    private Date dateCreated;
    private CulturalOfferDTO culturalOffer;
    private Set<Image> images;
}
