package com.sampleblog.event.listener;

import com.sampleblog.event.ArticleDeletedEvent;
import com.sampleblog.event.ArticleSavedEvent;
import com.sampleblog.search.ArticleSearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class ArticleSearchIndexListener {

    private ArticleSearchRepository repository;

    @TransactionalEventListener
    public void handleArticleSavedEvent(final ArticleSavedEvent articleSavedEvent) {
        repository.save(articleSavedEvent.getArticle());
    }

    @EventListener
    public void handleArticleDeletionEvent(final ArticleDeletedEvent articleDeletedEvent) {
        repository.deleteById(articleDeletedEvent.getArticleId());
    }
}
