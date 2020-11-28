package com.ktsnwt.Culturalcontentapp.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;
}
