package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
