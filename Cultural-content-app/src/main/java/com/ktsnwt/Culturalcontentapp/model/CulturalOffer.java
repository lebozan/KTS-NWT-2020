package com.ktsnwt.Culturalcontentapp.model;

import java.util.Collection;

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
    private String image;

    @OneToOne
    private Location location;

    @Column(nullable=false)
    private String description;

    // Stavili smo da Cultural Offer ima podtip kao svoj atribut umesto da se unutar podtipa nalazi kolekcija svih offera koji joj pripadaju
    @OneToOne
    private CulturalOfferSubType subtype;

//    private Collection<Rating> ratings;
//
//    private Collection<News> news;
}
