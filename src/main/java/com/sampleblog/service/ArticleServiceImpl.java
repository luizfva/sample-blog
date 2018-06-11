package com.sampleblog.service;

import com.sampleblog.event.ArticleDeletedEvent;
import com.sampleblog.event.ArticleSavedEvent;
import com.sampleblog.model.Article;
import com.sampleblog.repository.ArticleRepository;
import com.sampleblog.search.ArticleSearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ApplicationEventPublisher publisher;

    private final ArticleRepository articleRepository;

    private final ArticleSearchRepository searchRepository;

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