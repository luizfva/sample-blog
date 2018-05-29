package com.sampleblog.event;

import com.sampleblog.model.Article;

public class ArticleSavedEvent {

    private final Article article;

    public ArticleSavedEvent(final Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}
