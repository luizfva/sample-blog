package com.sampleblog.repository;

import com.sampleblog.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    List<Comment> findAll();

    List<Comment> findByArticleIdOrderByDate(Long articleId);
}
