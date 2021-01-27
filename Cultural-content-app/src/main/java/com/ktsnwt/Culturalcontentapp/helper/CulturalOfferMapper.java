package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferDTO;
import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferSubtypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.RatingDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferSubtype;
import com.ktsnwt.Culturalcontentapp.model.Rating;

import java.util.ArrayList;
import java.util.List;

public class CulturalOfferMapper implements MapperInterface<CulturalOffer, CulturalOfferDTO> {

    private final CulturalOfferSubtypeMapper costm;
    private RatingMapper ratingMapper;


    public CulturalOfferMapper() {
        costm = new CulturalOfferSubtypeMapper();
        ratingMapper = new RatingMapper();
    }

    @Override
    public CulturalOffer toEntity(CulturalOfferDTO dto) {
        return new CulturalOffer(dto.getName(),dto.getImages(),dto.getLocation(),dto.getDescription());
    }

	@Override
	public CulturalOfferDTO toDto(CulturalOffer entity) {
        CulturalOfferSubtypeDTO costDTO = costm.toDto(entity.getSubtype());
        List<RatingDTO> ratingDTOS = new ArrayList<>();
        for (Rating rating : entity.getRatings()) {
            ratingDTOS.add(ratingMapper.toDto(rating));
        }

		return new CulturalOfferDTO(entity.getId(), entity.getName(), null, null,
                entity.getDescription(), costDTO, ratingDTOS);
	}
    
}
