package com.sampleblog.service;

import com.sampleblog.model.Article;

import java.util.List;

public interface ArticleService {
    Article save(Article article);

    Article findById(Long id);

    void delete(Long id);

    List<Article> search(String title);
}
