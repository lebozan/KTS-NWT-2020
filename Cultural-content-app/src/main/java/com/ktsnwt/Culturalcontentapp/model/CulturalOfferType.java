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
public class CulturalOfferType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable=false, unique = true)
    private String name;


    @OneToMany(fetch = FetchType.LAZY)
    private Set<CulturalOfferSubtype> subtypes;

    public CulturalOfferType(String name) {
        this.name = name;
    }
}
