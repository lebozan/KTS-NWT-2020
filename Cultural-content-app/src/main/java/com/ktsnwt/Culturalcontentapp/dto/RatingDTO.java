package com.ktsnwt.Culturalcontentapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingDTO {

    private Long id;

    private Float ratingValue;

    private String comment;

    private List<ImageDTO> images;

    private UserDTO user;


}
