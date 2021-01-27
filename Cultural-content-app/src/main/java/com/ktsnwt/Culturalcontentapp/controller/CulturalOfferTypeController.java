package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferTypeDTO;
import com.ktsnwt.Culturalcontentapp.dto.PageDTO;
import com.ktsnwt.Culturalcontentapp.helper.CulturalOfferTypeMapper;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferType;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cultural-offer-types")
public class CulturalOfferTypeController {

    @Autowired
    CulturalOfferTypeService culturalOfferTypeService;

    private final CulturalOfferTypeMapper culturalOfferTypeMapper;

    public CulturalOfferTypeController() {
        culturalOfferTypeMapper = new CulturalOfferTypeMapper();
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<CulturalOfferTypeDTO>> getAllCulturalOfferTypes(@RequestParam int page, @RequestParam int size) {
        Page<CulturalOfferType> culturalOfferTypes = culturalOfferTypeService.findAll(PageRequest.of(page, size));
        Page<CulturalOfferTypeDTO> culturalOfferTypeDTOS = culturalOfferTypes.map(culturalOfferTypeMapper::toDto);


        return new ResponseEntity<>(culturalOfferTypeDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<CulturalOfferTypeDTO>> getAllCulturalOfferTypesList() {
        List<CulturalOfferType> culturalOfferTypes = culturalOfferTypeService.findAll();
        List<CulturalOfferTypeDTO> culturalOfferTypeDTOS = new ArrayList<>();
        for (CulturalOfferType type: culturalOfferTypes) {
            culturalOfferTypeDTOS.add(culturalOfferTypeMapper.toDto(type));
        }

        return new ResponseEntity<>(culturalOfferTypeDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CulturalOfferTypeDTO> getCulturalOfferType(@PathVariable Long id) {
        Optional<CulturalOfferType> culturalOfferType = culturalOfferTypeService.findById(id);

        if (culturalOfferType.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(culturalOfferTypeMapper.toDto(culturalOfferType.get()), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CulturalOfferTypeDTO> createCulturalOfferType(@RequestBody @Validated CulturalOfferTypeDTO culturalOfferTypeDTO) {
        CulturalOfferType newCulturalOfferType;
        try {
            newCulturalOfferType = culturalOfferTypeMapper.toEntity(culturalOfferTypeDTO);
            newCulturalOfferType = culturalOfferTypeService.create(newCulturalOfferType);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.culturalOfferTypeMapper.toDto(newCulturalOfferType), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CulturalOfferTypeDTO> updateCulturalOfferType(@RequestBody @Validated CulturalOfferTypeDTO culturalOfferTypeDTO,
                                                                        @PathVariable Long id) {
        Optional<CulturalOfferType> culturalOfferType = culturalOfferTypeService.findById(id);
        CulturalOfferType updatedCulturalOfferType;
        if (culturalOfferType.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            updatedCulturalOfferType = culturalOfferTypeService.update(culturalOfferType.get().getId(), culturalOfferTypeDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(culturalOfferTypeMapper.toDto(updatedCulturalOfferType), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCulturalOfferType(@PathVariable Long id) {
        Optional<CulturalOfferType> culturalOfferType = culturalOfferTypeService.findById(id);

        try {
            culturalOfferTypeService.delete(culturalOfferType.orElseThrow().getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
    
}
