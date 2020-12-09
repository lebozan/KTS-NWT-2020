package com.ktsnwt.Culturalcontentapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthTokenDTO {

    private String accessToken;
    private long expiresIn;

}
