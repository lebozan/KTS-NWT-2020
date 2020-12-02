package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.model.RegisteredUser;
import com.ktsnwt.Culturalcontentapp.model.User;

public class UserMapper implements MapperInterface<User, UserDTO> {
    @Override
    public User toEntity(UserDTO dto) {
        return new RegisteredUser(null,dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword());
    }

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(entity.getFirstName(), entity.getLastName(), entity.getEmail(), "*".repeat(entity.getPassword().length()), entity.getRole());
    }
}
