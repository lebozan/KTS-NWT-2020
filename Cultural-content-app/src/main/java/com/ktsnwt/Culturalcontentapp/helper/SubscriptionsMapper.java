package com.ktsnwt.Culturalcontentapp.helper;

import com.ktsnwt.Culturalcontentapp.dto.SubscriptionsDTO;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SubscriptionsMapper implements MapperInterface<Set<CulturalOffer>, SubscriptionsDTO> {
    @Override
    public Set<CulturalOffer> toEntity(SubscriptionsDTO dto) {
        return null;
    }

    @Override
    public SubscriptionsDTO toDto(Set<CulturalOffer> entity) {
        List<Long> subs = new ArrayList<>();
        for (CulturalOffer cu : entity) {
           subs.add(cu.getId());
        }

        return new SubscriptionsDTO(subs);
    }
}
