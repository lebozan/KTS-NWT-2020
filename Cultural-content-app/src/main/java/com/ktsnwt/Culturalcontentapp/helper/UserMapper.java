package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.RegisterDTO;
import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.model.RegisteredUser;
import com.ktsnwt.Culturalcontentapp.model.User;

public class UserMapper implements MapperInterface<User, UserDTO> {
    @Override
    public User toEntity(UserDTO dto) {
        return new RegisteredUser(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getRole());
    }

    public RegisteredUser toRegisterEntity(RegisterDTO registerDTO) {
        return new RegisteredUser(registerDTO.getFirstName(), registerDTO.getLastName(),
                registerDTO.getEmail(), registerDTO.getPassword());
    }
}
