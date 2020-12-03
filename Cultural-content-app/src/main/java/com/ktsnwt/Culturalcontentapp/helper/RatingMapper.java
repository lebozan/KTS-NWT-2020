package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.RatingDTO;
import com.ktsnwt.Culturalcontentapp.dto.UserDTO;
import com.ktsnwt.Culturalcontentapp.model.Image;
import com.ktsnwt.Culturalcontentapp.model.Rating;
import com.ktsnwt.Culturalcontentapp.model.RegisteredUser;
import com.ktsnwt.Culturalcontentapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class RatingMapper implements MapperInterface<Rating, RatingDTO> {
    @Override
    public Rating toEntity(RatingDTO dto) {
        return new Rating(null, dto.getRatingValue(), dto.getComment(), null, null);
    }

    @Override
    public RatingDTO toDto(Rating entity) {
        List<String> images = new ArrayList<>();
        for (Image i : entity.getImages()) {
            images.add(i.getAddressURL());
        }

        return new RatingDTO(entity.getRatingValue(), entity.getComment(), images,
                new UserDTO(entity.getUser().getFirstName(), entity.getUser().getLastName(), entity.getUser().getEmail(),
                        entity.getUser().getPassword(), entity.getUser().getRole()));
    }
}
