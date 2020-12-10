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
        return new Rating(dto.getRatingValue(), dto.getComment());
    }

    @Override
    public RatingDTO toDto(Rating entity) {
        List<String> images = new ArrayList<>();
        if (entity.getImages() != null) {
            for (Image i : entity.getImages()) {
                images.add(i.getAddressURL());
            }
        }

        return new RatingDTO(entity.getId(), entity.getRatingValue(), entity.getComment(), images,
                new UserDTO(entity.getUser().getId(), entity.getUser().getFirstName(), entity.getUser().getLastName(),
                        entity.getUser().getEmail()));
    }
}
