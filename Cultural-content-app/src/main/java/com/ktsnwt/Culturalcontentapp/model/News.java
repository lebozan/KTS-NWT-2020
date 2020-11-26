package com.ktsnwt.Culturalcontentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

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
    private CulturalOffer culturalOfferId;

    @Column
    private String image;
    
}
