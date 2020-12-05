package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;

public class CulturalOfferTypeMapper implements MapperInterface<CulturalOfferType, CulturalOfferTypeDTO>{

    @Override
    public CulturalOfferType toEntity(CulturalOfferTypeDTO dto) {
        return new CulturalOfferType(dto.getName());
    }

    @Override
    public CulturalOfferTypeDTO toDto(CulturalOfferType entity) {
        return new CulturalOfferTypeDTO(entity.getId(), entity.getName());
    }
}
