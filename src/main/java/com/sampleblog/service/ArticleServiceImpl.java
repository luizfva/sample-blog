package com.sampleblog.service;

import com.sampleblog.event.ArticleDeletedEvent;
import com.sampleblog.event.ArticleSavedEvent;
import com.sampleblog.model.Article;
import com.sampleblog.repository.ArticleRepository;
import com.sampleblog.search.ArticleSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleSearchRepository searchRepository;

    @Override
    @CachePut(key = "#article.id")
    public Article save(final Article article) {
        final Article savedArticle = articleRepository.save(article);
        publisher.publishEvent(new ArticleSavedEvent(savedArticle));
        return savedArticle;
    }

    @Override
    @Cacheable(key = "#id")
    public Article findById(final Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(final Long id) {
        articleRepository.deleteById(id);
        publisher.publishEvent(new ArticleDeletedEvent(id));
    }

    @Override
    public List<Article> search(final String search) {
        return searchRepository
                .findTop10ByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(search, search);
    }

}