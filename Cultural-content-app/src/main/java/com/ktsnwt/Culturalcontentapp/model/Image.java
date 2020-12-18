package com.ktsnwt.Culturalcontentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String addressURL;


    @OneToOne
    private Rating rating;

    @OneToOne
    private News news;

    public Image(long id, String addressURL) {
        this.id = id;
        this.addressURL = addressURL;
    }
}
