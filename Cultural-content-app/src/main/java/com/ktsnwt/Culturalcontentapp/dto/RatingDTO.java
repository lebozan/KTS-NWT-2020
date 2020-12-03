package com.ktsnwt.Culturalcontentapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingDTO {

    private float rating;

    private String comment;

    private List<String> images;

    private UserDTO user;


}
