package com.ktsnwt.Culturalcontentapp.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ktsnwt.Culturalcontentapp.constants.NewsConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ktsnwt.Culturalcontentapp.model.News;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class NewsRepositoryIntegrationTest {

    @Autowired
    NewsRepository NewsRepository;

    @Test
    void testFindByTitle() {
        News existingNews = NewsRepository.findByTitle(EXISTING_NEWS_TITLE);
        assertEquals(EXISTING_NEWS_TITLE, existingNews.getTitle());
    }

    @Test
    void testFindByTitle_NotFound() {
        News existingNews = NewsRepository.findByTitle(NOT_EXISTING_NEWS_TITLE);
        assertNull(existingNews);
    }
    
}
