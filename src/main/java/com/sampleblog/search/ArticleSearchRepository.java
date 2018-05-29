package com.sampleblog.search;

import com.sampleblog.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long> {
    List<Article> findTop10ByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title,
                                                                                    String content);
}
