package com.ktsnwt.Culturalcontentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float ratingValue;

    @Column
    private String comment;

    @OneToMany
    private Set<Image> images;

    @OneToOne
    private User user;

    public Rating(float ratingValue, String comment) {
        this.ratingValue = ratingValue;
        this.comment = comment;
    }
}
