package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferDTO;
import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferSubtypeDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferSubtype;

public class CulturalOfferMapper implements MapperInterface<CulturalOffer, CulturalOfferDTO> {

    private final CulturalOfferSubtypeMapper costm;


    public CulturalOfferMapper() {
        costm = new CulturalOfferSubtypeMapper();
    }

    @Override
    public CulturalOffer toEntity(CulturalOfferDTO dto) {
        return new CulturalOffer(dto.getName(),dto.getImages(),dto.getLocation(),dto.getDescription());
    }

	@Override
	public CulturalOfferDTO toDto(CulturalOffer entity) {
        CulturalOfferSubtypeDTO costDTO = costm.toDto(entity.getSubtype());

		return new CulturalOfferDTO(entity.getId(), entity.getName(), entity.getImages(), entity.getLocation(),
                entity.getDescription(), costDTO, entity.getRatings(), entity.getNews());
	}
    
}
