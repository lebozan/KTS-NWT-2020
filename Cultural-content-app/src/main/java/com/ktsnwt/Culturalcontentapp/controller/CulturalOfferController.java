package com.ktsnwt.Culturalcontentapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.ktsnwt.Culturalcontentapp.dto.CulturalOfferDTO;
import com.ktsnwt.Culturalcontentapp.helper.CulturalOfferMapper;
import com.ktsnwt.Culturalcontentapp.model.CulturalOffer;
import com.ktsnwt.Culturalcontentapp.service.CulturalOfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cultural-offers")
public class CulturalOfferController {

    @Autowired
    CulturalOfferService culturalOfferService;

    private CulturalOfferMapper culturalOfferMapper;

    public CulturalOfferController() {
        this.culturalOfferMapper = new CulturalOfferMapper();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CulturalOfferDTO>> getAllCulturalOffers() {
        List<CulturalOffer> culturalOffers = culturalOfferService.findAll();
        List<CulturalOfferDTO> culturalOfferDTOS = new ArrayList<>();
        for (CulturalOffer co : culturalOffers) {
            culturalOfferDTOS.add(culturalOfferMapper.toDto(co));
        }

        return new ResponseEntity<>(culturalOfferDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<CulturalOfferDTO> getCulturalOffer(@PathVariable String name) {
        CulturalOffer culturalOffer = culturalOfferService.findByName(name);
        if (culturalOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(culturalOfferMapper.toDto(culturalOffer), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CulturalOfferDTO> createCulturalOffer(@RequestBody CulturalOfferDTO culturalOfferDTO) {
        CulturalOffer newCulturalOffer;
        try {
            newCulturalOffer = culturalOfferMapper.toEntity(culturalOfferDTO);
            newCulturalOffer = culturalOfferService.create(newCulturalOffer);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.culturalOfferMapper.toDto(newCulturalOffer), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCulturalOffer(@PathVariable String name) {
        CulturalOffer culturalOffer = culturalOfferService.findByName(name);

        try {
            culturalOfferService.delete(culturalOffer.getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    public ResponseEntity<CulturalOfferDTO> updateCulturalOffer(@RequestBody CulturalOfferDTO culturalOfferDTO,
                                                                @PathVariable String name) {
        CulturalOffer culturalOffer = culturalOfferService.findByName(name);
        if (culturalOffer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            culturalOfferService.update(culturalOffer.getId(), culturalOfferDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
