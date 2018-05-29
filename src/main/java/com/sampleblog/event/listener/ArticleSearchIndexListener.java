package com.sampleblog.event.listener;

import com.sampleblog.event.ArticleDeletedEvent;
import com.sampleblog.event.ArticleSavedEvent;
import com.sampleblog.search.ArticleSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ArticleSearchIndexListener {

    @Autowired
    ArticleSearchRepository repository;

    @EventListener
    public void handleArticleSavedEvent(final ArticleSavedEvent articleSavedEvent) {
        repository.save(articleSavedEvent.getArticle());
    }

    @EventListener
    public void handleArticleDeletionEvent(final ArticleDeletedEvent articleDeletedEvent) {
        repository.deleteById(articleDeletedEvent.getArticleId());
    }
}
