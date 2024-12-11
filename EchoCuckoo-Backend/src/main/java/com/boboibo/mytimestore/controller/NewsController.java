package com.boboibo.mytimestore.controller;


import com.boboibo.mytimestore.model.entity.News;
import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.request.NewsRequest;
import com.boboibo.mytimestore.model.request.ProductRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.service.NewService;
import com.boboibo.mytimestore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news")
@EnableCaching
@RequiredArgsConstructor
public class NewsController {
    NewService newService;
    @Cacheable(value = "newsCache", key = "#root.getAllNews")
    @GetMapping
    public ResponseEntity<ResponseObject> getAllNews() {
        List<News> newsList = newService.getAllNews();
        if(newsList.isEmpty()){
            return ResponseObject.APIRepsonse(404, "No content", HttpStatus.NOT_FOUND,null);
        }
        return ResponseObject.APIRepsonse(200, "Fetched all news successfully", HttpStatus.OK, newsList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getNewsById(@PathVariable Long id) {
        News news = newService.getNewsById(id);
        if (news != null) {
            return ResponseObject.APIRepsonse(200, "Fetched news successfully", HttpStatus.OK, news);
        } else {
            return ResponseObject.APIRepsonse(404, "News not found", HttpStatus.NOT_FOUND, null);
        }
    }
    @PostMapping("")
    public ResponseEntity<ResponseObject> createNews(@RequestBody NewsRequest news) {
        boolean isCreated = newService.createNews(news);
        if (isCreated) {
            return ResponseObject.APIRepsonse(200, "Create new news successfully", HttpStatus.OK, null);
        } else {
            return ResponseObject.APIRepsonse(409, "News title already exists", HttpStatus.CONFLICT, null);
        }
    }
    @PutMapping("")
    public ResponseEntity<ResponseObject> updateNews(@RequestParam Long newId,@RequestBody NewsRequest newsDetails) {
        boolean isUpdated = newService.updateNews(newId, newsDetails);
        if (isUpdated) {
            return ResponseObject.APIRepsonse(200, "Update news successfully", HttpStatus.OK, newsDetails);
        } else {
            return ResponseObject.APIRepsonse(400, "Failed to update news", HttpStatus.BAD_REQUEST, null);
        }
    }
    @DeleteMapping("/{newsId}")
    public ResponseEntity<ResponseObject> deleteNews(@PathVariable Long newsId) {
        try {
            boolean isDeleted = newService.deleteNews(newsId);
            if (isDeleted) {
                return ResponseObject.APIRepsonse(200, "Delete news successfully", HttpStatus.OK, null);
            } else {
                return ResponseObject.APIRepsonse(404, "Not found to delete news", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseObject.APIRepsonse(500, "An error occurred while deleting news", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
