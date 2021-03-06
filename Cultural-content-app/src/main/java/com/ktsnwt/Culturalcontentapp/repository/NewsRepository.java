package com.ktsnwt.Culturalcontentapp.repository;

import com.ktsnwt.Culturalcontentapp.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {

    News findByTitle(String title);
}
