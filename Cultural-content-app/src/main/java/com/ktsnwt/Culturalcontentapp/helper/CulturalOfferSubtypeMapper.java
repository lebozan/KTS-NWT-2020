package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferSubtypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferSubtype;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;

public class CulturalOfferSubtypeMapper implements MapperInterface<CulturalOfferSubtype, CulturalOfferSubtypeDTO> {
    @Override
    public CulturalOfferSubtype toEntity(CulturalOfferSubtypeDTO dto) {
        return new CulturalOfferSubtype(dto.getName());
    }

    @Override
    public CulturalOfferSubtypeDTO toDto(CulturalOfferSubtype entity) {
        return new CulturalOfferSubtypeDTO(entity.getName(), new CulturalOfferTypeDTO(entity.getName()));
    }
}
