package com.ktsnwt.Culturalcontentapp.dto;

import com.ktsnwt.Culturalcontentapp.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

}
