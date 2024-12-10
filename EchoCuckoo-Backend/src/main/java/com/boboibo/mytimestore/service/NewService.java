package com.boboibo.mytimestore.service;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.model.entity.News;
import com.boboibo.mytimestore.model.request.NewsRequest;
import com.boboibo.mytimestore.repository.NewsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewService {

    @Autowired
    NewsRepository newsRepository;
    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        List<News> list = newsRepository.findAll();
        list.stream().forEach(news -> {
            if(news.isStatus()){
                newsList.add(news);
            }
        });
        return newsList;
    }


    public News getNewsById(String id) {
        News news = newsRepository.findBynewId(id);
        if (news != null && news.isStatus()) {
            return news;
        } else {
            throw new AppException(ErrorCode.NEWS_NOT_EXIST);
        }
    }

    public boolean createNews(NewsRequest news) {
        try {
            News news1 = News.builder()
                    .img(news.getImg())
                    .title(news.getTitle())
                    .content(news.getContent())
                    .preview(news.getPreview())
                    .status(true)
                    .build();
            newsRepository.save(news1);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateNews(String newId, NewsRequest newsDetails) {
        try {
            News optionalNews = newsRepository.findBynewId(newId);
            if (optionalNews != null) {
                optionalNews.setTitle(newsDetails.getTitle());
                optionalNews.setContent(newsDetails.getContent());
                optionalNews.setPreview(newsDetails.getPreview());
                optionalNews.setImg(newsDetails.getImg());
                newsRepository.save(optionalNews);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNews(String newId) {
        try {
            News news = getNewsById(newId);
            news.setStatus(false);
            newsRepository.save(news);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
