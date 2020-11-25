package com.ktsnwt.Culturalcontentapp.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor()
@Data
@Entity
public class RegisteredUser extends User{

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "UserSubscriptions",
            joinColumns = { @JoinColumn(name = "userId")},
            inverseJoinColumns = { @JoinColumn(name = "")}
    )
    private Set<CulturalOffer> subscriptions;

    @Column
    private boolean active;



}
