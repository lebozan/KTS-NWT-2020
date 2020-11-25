package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, String> {
}
