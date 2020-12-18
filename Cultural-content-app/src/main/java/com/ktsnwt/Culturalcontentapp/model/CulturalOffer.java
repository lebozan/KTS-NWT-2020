package com.ktsnwt.Culturalcontentapp.model;


import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class CulturalOffer {

    public CulturalOffer(String name2, Set<Image> images2, Location location2, String description2) {
        this.name = name2;
        this.images = images2;
        this.location = location2;
        this.description = description2;
	}

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
    
    @OneToOne(fetch = FetchType.EAGER)
    private CulturalOfferSubtype subtype;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Rating> ratings;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<News> news;
}
