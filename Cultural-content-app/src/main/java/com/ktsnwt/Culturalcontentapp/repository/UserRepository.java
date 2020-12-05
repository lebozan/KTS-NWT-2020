package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.Role;
import com.ktsnwt.Culturalcontentapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

    User findByFirstNameAndLastName(String firstName, String lastName);

    Page<User> findAllByRole(Pageable pageable, Role role);

}
