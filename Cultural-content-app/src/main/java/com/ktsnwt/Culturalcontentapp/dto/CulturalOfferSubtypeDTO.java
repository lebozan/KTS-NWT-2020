package com.ktsnwt.Culturalcontentapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CulturalOfferSubtypeDTO {

    private Long id;
    private String name;
    private CulturalOfferTypeDTO type;

}
