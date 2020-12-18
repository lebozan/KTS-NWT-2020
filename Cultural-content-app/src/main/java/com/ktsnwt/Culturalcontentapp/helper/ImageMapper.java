package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.ImageDTO;
import com.ktsnwt.Culturalcontentapp.model.Image;

public class ImageMapper  implements MapperInterface<Image, ImageDTO> {
    @Override
    public Image toEntity(ImageDTO dto) {
        return new Image(dto.getId(), dto.getAddressURL());
    }

    @Override
    public ImageDTO toDto(Image entity) {
        return new ImageDTO(entity.getId(), entity.getAddressURL(), entity.getRating().getId(), entity.getNews().getTitle());
    }
}
