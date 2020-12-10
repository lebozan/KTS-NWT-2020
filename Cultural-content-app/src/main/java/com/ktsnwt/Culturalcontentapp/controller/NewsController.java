package com.ktsnwt.Culturalcontentapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ktsnwt.Culturalcontentapp.dto.NewsDTO;
import com.ktsnwt.Culturalcontentapp.helper.NewsMapper;
import com.ktsnwt.Culturalcontentapp.model.News;
import com.ktsnwt.Culturalcontentapp.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    private final NewsMapper newsMapper;

    public NewsController(){
        this.newsMapper = new NewsMapper();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<News> news = newsService.findAll();
        List<NewsDTO> newsDTOs = new ArrayList<>();
        for (News ne : news) {
            newsDTOs.add(newsMapper.toDto(ne));
        }

        return new ResponseEntity<>(newsDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/{title}", method = RequestMethod.GET)
    public ResponseEntity<NewsDTO> getNews(@PathVariable String title) {
        News news = newsService.findById(title);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newsMapper.toDto(news), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) {
        News newNews;
        try {
            newNews = newsMapper.toEntity(newsDTO);
            newNews = newsService.create(newNews);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.newsMapper.toDto(newNews), HttpStatus.OK);
    }

    @RequestMapping(value = "/{title}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteNews(@PathVariable String title) {
        News news = newsService.findById(title);

        try {
            newsService.delete(news.getTitle());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{title}", method = RequestMethod.PUT)
    public ResponseEntity<NewsDTO> updateNews(@RequestBody NewsDTO newsDTO,
                                                                @PathVariable String title) {
        News news = newsService.findById(title);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            newsService.update(news.getTitle(), newsDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
