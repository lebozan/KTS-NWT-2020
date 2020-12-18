package com.ktsnwt.Culturalcontentapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("User")
public class RegisteredUser extends User{

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cultural_offer_id", referencedColumnName = "id")
    )
    private Set<CulturalOffer> subscriptions;

    @Column
    private boolean active;


    public RegisteredUser(Long id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
        this.active = false;
        this.subscriptions = null;

    }

    public RegisteredUser(String email, String password) {
        super(email, password);
        this.active = false;
        this.subscriptions = null;
    }

    public RegisteredUser(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.active = false;
        this.subscriptions = null;
    }

    public Set<CulturalOffer> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<CulturalOffer> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "subscriptions=" + subscriptions +
                ", active=" + active +
                '}';
    }
}
