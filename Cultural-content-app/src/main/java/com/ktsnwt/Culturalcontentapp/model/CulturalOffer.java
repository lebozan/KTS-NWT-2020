package com.ktsnwt.Culturalcontentapp.model;

import java.util.Set;

import javax.persistence.*;

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
    private Long id;

    @Column(nullable=false, unique = true)
    private String name;

    @Column(nullable=false)
    @OneToMany
    private Set<Image> images;

    @OneToOne
    private Location location;

    @Column(nullable=false)
    private String description;
    
    @OneToOne
    private CulturalOfferSubType subtype;

    @OneToMany
    private Set<Rating> ratings;

    @OneToMany
    private Set<News> news;
}
