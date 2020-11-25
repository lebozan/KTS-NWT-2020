package com.ktsnwt.Culturalcontentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class News {

    @Id
    private String title;

    private String text;

    private Date dateCreated;

    private Long culturalOfferId;

    private String image;
    
}
