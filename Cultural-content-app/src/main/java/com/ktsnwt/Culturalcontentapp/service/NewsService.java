package com.ktsnwt.Culturalcontentapp.service;

import com.ktsnwt.Culturalcontentapp.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;
}
