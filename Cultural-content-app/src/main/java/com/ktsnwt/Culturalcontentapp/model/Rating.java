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
    private Long id;

    @Column(nullable = false)
    private float rating;

    @Column
    private String comment;

    @OneToMany
    private Set<Image> images;

    @OneToOne()
    private User user;
    
}
