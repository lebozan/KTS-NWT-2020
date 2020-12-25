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

    public News(String text2, Date dateCreated2) {
        this.text = text2;
        this.dateCreated = dateCreated2;
	}

	@Id
    private String title;

    @Column
    private String text;

    @Column
    private Date dateCreated;

    @OneToOne(fetch = FetchType.LAZY)
    private CulturalOffer culturalOffer;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Image> images;
    
}
