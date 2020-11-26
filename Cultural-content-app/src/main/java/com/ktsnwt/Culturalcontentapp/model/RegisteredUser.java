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

    @ManyToMany()
    // da li treba zbog ove anotacije da CulturalOffer ima listu korisnika ili da to samo bude posebna join tabela?
    private Set<CulturalOffer> subscriptions;

    @Column
    private boolean active;



}
