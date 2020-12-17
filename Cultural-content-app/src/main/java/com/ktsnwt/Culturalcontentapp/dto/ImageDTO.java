package com.ktsnwt.Culturalcontentapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageDTO {

    private long id;
    private String addressURL;
    private long ratingId;
    private String newsTitle;


}
