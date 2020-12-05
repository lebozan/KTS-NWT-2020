package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferSubtypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.PageDTO;
import com.ktsnwt.Culturalcontentapp.helper.CulturalOfferSubtypeMapper;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferSubtype;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferSubtypeService;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cultural-offer-subtypes")
public class CulturalOfferSubtypeController {

    @Autowired
    CulturalOfferSubtypeService culturalOfferSubtypeService;

    @Autowired
    CulturalOfferTypeService culturalOfferTypeService;

    private final CulturalOfferSubtypeMapper culturalOfferSubtypeMapper;

    public CulturalOfferSubtypeController() {
        culturalOfferSubtypeMapper = new CulturalOfferSubtypeMapper();
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<CulturalOfferSubtypeDTO>> getAllCulturalOfferSubtypes(@RequestBody @Validated PageDTO pageDTO) {
        Page<CulturalOfferSubtype> culturalOfferSubtypes = culturalOfferSubtypeService.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
        Page<CulturalOfferSubtypeDTO> culturalOfferSubtypeDTOS = culturalOfferSubtypes.map(culturalOfferSubtypeMapper::toDto);

        return new ResponseEntity<>(culturalOfferSubtypeDTOS, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CulturalOfferSubtypeDTO> getCulturalOfferSubtype(@PathVariable Long id) {
        Optional<CulturalOfferSubtype> culturalOfferSubtype = culturalOfferSubtypeService.findById(id);
        if (culturalOfferSubtype.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(culturalOfferSubtypeMapper.toDto(culturalOfferSubtype.get()), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CulturalOfferSubtypeDTO> createCulturalOfferSubtype(@RequestBody @Validated CulturalOfferSubtypeDTO culturalOfferSubtypeDTO) {
        CulturalOfferSubtype newCulturalOfferSubtype;
        try {
            newCulturalOfferSubtype = culturalOfferSubtypeMapper.toEntity(culturalOfferSubtypeDTO);
            newCulturalOfferSubtype.setType(culturalOfferTypeService.findByName(culturalOfferSubtypeDTO.getType().getName()));
            newCulturalOfferSubtype = culturalOfferSubtypeService.create(newCulturalOfferSubtype);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(culturalOfferSubtypeMapper.toDto(newCulturalOfferSubtype), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CulturalOfferSubtypeDTO> updateCulturalOfferSubtype(@RequestBody @Validated CulturalOfferSubtypeDTO culturalOfferSubtypeDTO,
                                                                        @PathVariable Long id) {
        Optional<CulturalOfferSubtype> culturalOfferSubtype = culturalOfferSubtypeService.findById(id);
        if (culturalOfferSubtype.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            culturalOfferSubtypeService.update(culturalOfferSubtype.get().getId(), culturalOfferSubtypeDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCulturalOfferSubtype(@PathVariable Long id) {
        Optional<CulturalOfferSubtype> culturalOfferSubtype = culturalOfferSubtypeService.findById(id);

        if (culturalOfferSubtype.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            culturalOfferSubtypeService.delete(culturalOfferSubtype.get().getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
    
}
