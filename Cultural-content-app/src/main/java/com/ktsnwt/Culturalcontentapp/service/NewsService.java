package com.ktsnwt.Culturalcontentapp.service;

import java.util.List;
import java.util.Optional;

import com.ktsnwt.Culturalcontentapp.dto.NewsDTO;
import com.ktsnwt.Culturalcontentapp.model.News;
import com.ktsnwt.Culturalcontentapp.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public News findById(String title) {
        return newsRepository.findByTitle(title);
    }

    public News create(News news) throws Exception {
        if (newsRepository.findById(news.getTitle()) != null) {
            throw new Exception("News with same title already exist!");
        }

        return newsRepository.save(news);
    }

    public void delete(String title) throws Exception {
        Optional<News> existingNews = newsRepository.findById(title);
        if (existingNews.isEmpty()) {
            throw new Exception("News with this title don't exist!");
        }

        newsRepository.delete(existingNews.get());
    }

    public void update(String title, NewsDTO updateDTO) throws Exception {
        Optional<News> existingNews = newsRepository.findById(title);
        if (existingNews.isEmpty()) {
            throw new Exception("News with this title don't exist!");
        }
        News news = existingNews.get();

        news.setText(updateDTO.getText());
        //news.setDateCreated(updateDTO.getDateCreated());

        newsRepository.save(news);
    }
}
