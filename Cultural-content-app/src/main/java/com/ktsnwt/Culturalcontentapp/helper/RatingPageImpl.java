package com.ktsnwt.Culturalcontentapp.helper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class RatingPageImpl<RatingDTO> extends PageImpl<RatingDTO> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RatingPageImpl(@JsonProperty("content") List<RatingDTO> content,
                                     @JsonProperty("number") int page,
                                     @JsonProperty("size") int size,
                                     @JsonProperty("totalElements") Long totalElements,
                                     @JsonProperty("pageable") JsonNode pageable,
                                     @JsonProperty("last") boolean last,
                                     @JsonProperty("totalPages") int totalPages,
                                     @JsonProperty("sort") JsonNode sort,
                                     @JsonProperty("first") boolean first,
                                     @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, (Pageable) PageRequest.of(page, size), totalElements);
    }


    public RatingPageImpl(List<RatingDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RatingPageImpl(List<RatingDTO> content) {
        super(content);
    }

    public RatingPageImpl() {
        super(new ArrayList<RatingDTO>());
    }

}
