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
@DiscriminatorValue("User")
public class RegisteredUser extends User{

    @ManyToMany()
    @JoinTable( name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cultural_offer_id", referencedColumnName = "id")
    )
    private Set<CulturalOffer> subscriptions;

    @Column
    private boolean active;


    public RegisteredUser(Long id, String firstName, String lastName, String email, String password) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(Role.REGISTERED_USER);
        this.active = false;
        this.subscriptions = null;

    }
}
