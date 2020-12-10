package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferDTO;
import com.ktsnwt.Culturalcontentapp.dto.NewsDTO;
import com.ktsnwt.Culturalcontentapp.model.News;

public class NewsMapper implements MapperInterface<News, NewsDTO> {

    private final CulturalOfferMapper com;

    public NewsMapper() {
        com = new CulturalOfferMapper();
    }

    @Override
    public News toEntity(NewsDTO dto) {
        return new News(dto.getText(),dto.getDateCreated());
    }

    @Override
    public NewsDTO toDto(News entity) {
        CulturalOfferDTO coDTO = com.toDto(entity.getCulturalOffer());
        return new NewsDTO(entity.getTitle(),entity.getText(),entity.getDateCreated(),coDTO ,entity.getImages());
    }
    
}
