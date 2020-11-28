package com.ktsnwt.Culturalcontentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class News {

    @Id
    private String title;

    @Column
    private String text;

    @Column
    private Date dateCreated;

    @OneToOne
    private CulturalOffer culturalOffer;

    @OneToMany
    private Set<Image> images;
    
}
