package com.ktsnwt.Culturalcontentapp.controller;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferSubtypeDTO;
import com.ktsnwt.Culturalcontentapp.helper.CulturalOfferSubtypeMapper;
import com.ktsnwt.Culturalcontentapp.model.CulturalOfferSubtype;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferSubtypeService;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    // http://localhost:8080/api/cultural-offer-subtypes
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CulturalOfferSubtypeDTO>> getAllCulturalOfferSubtypes() {
        List<CulturalOfferSubtype> culturalOfferSubtypes = culturalOfferSubtypeService.findAll();
        List<CulturalOfferSubtypeDTO> culturalOfferSubtypeDTOS = new ArrayList<>();
        for (CulturalOfferSubtype c : culturalOfferSubtypes) {
            culturalOfferSubtypeDTOS.add(culturalOfferSubtypeMapper.toDto(c));
        }

        return new ResponseEntity<>(culturalOfferSubtypeDTOS, HttpStatus.OK);
    }

    // http://localhost:8080/api/cultural-offer-subtypes/Muzej
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<CulturalOfferSubtypeDTO> getCulturalOfferSubtype(@PathVariable String name) {
        CulturalOfferSubtype culturalOfferSubtype = culturalOfferSubtypeService.findByName(name);
        if (culturalOfferSubtype == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(culturalOfferSubtypeMapper.toDto(culturalOfferSubtype), HttpStatus.OK);
    }

    // http://localhost:8080/api/cultural-offer-subtypes/create
    //{
    //    "name": "Podtip",
    //    "type": {
    //        "name": "Institucija"
    //    }
    //}
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CulturalOfferSubtypeDTO> createCulturalOfferSubtype(@RequestBody CulturalOfferSubtypeDTO culturalOfferSubtypeDTO) {
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

    // http://localhost:8080/api/cultural-offer-subtypes/Muzej
    //
    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    public ResponseEntity<CulturalOfferSubtypeDTO> updateCulturalOfferSubtype(@RequestBody CulturalOfferSubtypeDTO culturalOfferSubtypeDTO,
                                                                        @PathVariable String name) {
        CulturalOfferSubtype culturalOfferSubtype = culturalOfferSubtypeService.findByName(name);
        if (culturalOfferSubtype == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            culturalOfferSubtypeService.update(culturalOfferSubtype.getId(), culturalOfferSubtypeDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:8080/api/cultural-offer-subtypes/Muzej
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCulturalOfferSubtype(@PathVariable String name) {
        CulturalOfferSubtype culturalOfferSubtype = culturalOfferSubtypeService.findByName(name);

        try {
            culturalOfferSubtypeService.delete(culturalOfferSubtype.getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
    
}
