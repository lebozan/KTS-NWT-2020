package com.ktsnwt.Culturalcontentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @Column(nullable = false)
    private float rating;

    @Column
    private String comment;


    // da li slika ipak treba biti klasa pa da se sa ManyToMany povezu slike sa ocenom kojoj pripadaju
    @OneToMany
    private Set<Image> images;

    @OneToOne
    private User userId;
    
}
