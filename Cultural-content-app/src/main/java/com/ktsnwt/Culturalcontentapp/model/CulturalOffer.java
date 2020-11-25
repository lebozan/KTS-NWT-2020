package com.ktsnwt.Culturalcontentapp.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class CulturalOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String address;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String image;

    @Column(nullable=false)
    private Location location;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private CulturalOfferSubType subtype;

    private Collection<Rating> ratings;

    private Collection<News> news;
}
