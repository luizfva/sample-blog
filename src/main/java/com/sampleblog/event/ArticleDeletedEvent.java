package com.sampleblog.event;

public class ArticleDeletedEvent {
    private final Long articleId;

    public ArticleDeletedEvent(final Long articleId) {
        this.articleId = articleId;
    }

    public Long getArticleId() {
        return articleId;
    }
}
