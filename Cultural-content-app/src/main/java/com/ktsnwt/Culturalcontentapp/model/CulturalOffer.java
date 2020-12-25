package com.ktsnwt.Culturalcontentapp.model;


import java.util.Objects;
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
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Image> images;

    @OneToOne(fetch = FetchType.EAGER)
    private Location location;

    @Column(nullable=false)
    private String description;
    
    @OneToOne(fetch = FetchType.EAGER)
    private CulturalOfferSubtype subtype;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Rating> ratings;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<News> news;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalOffer that = (CulturalOffer) o;
        return id.equals(that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
