package com.ktsnwt.Culturalcontentapp.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor()
@Data
@Entity
public class RegisteredUser extends User{

    private Collection<Integer> subscriptions;

    private boolean active;



}
