package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;

public class CulturalOfferMapper implements MapperInterface<CulturalOffer, CulturalOfferDTO> {

    @Override
    public CulturalOffer toEntity(CulturalOfferDTO dto) {
        return new CulturalOffer(dto.getName(),dto.getImages(),dto.getLocation(),dto.getDescription());
    }

	@Override
	public CulturalOfferDTO toDto(CulturalOffer entity) {
		return new CulturalOfferDTO(entity.getName(), entity.getImages(), entity.getLocation(), entity.getDescription());
	}
    
}
