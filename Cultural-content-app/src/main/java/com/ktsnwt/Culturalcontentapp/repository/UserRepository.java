package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
