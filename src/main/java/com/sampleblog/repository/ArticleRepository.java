package com.sampleblog.repository;

import com.sampleblog.model.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    List<Article> findTop10ByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title,
                                                                                    String content);

}
