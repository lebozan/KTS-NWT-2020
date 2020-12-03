package com.ktsnwt.Culturalcontentapp.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class CulturalOfferSubtype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique = true)
    private String name;

    @OneToOne
    private CulturalOfferType type;


    public CulturalOfferSubtype(String name) {
        this.name = name;
    }

    public CulturalOfferSubtype(String name, CulturalOfferType culturalOfferType) {
        this.name = name;
        this.type = culturalOfferType;
    }
}
