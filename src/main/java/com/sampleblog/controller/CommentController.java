package com.sampleblog.controller;

import com.sampleblog.model.Comment;
import com.sampleblog.service.ArticleService;
import com.sampleblog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final ArticleService articleService;

    @PostMapping(path = "articles/{article-id}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "article-id") Long articleId,
                                                 @RequestBody Comment comment) {
        comment.setArticle(articleService.findById(articleId));
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }

    @GetMapping(path = "articles/{article-id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("article-id") Long articleId) {
        return new ResponseEntity<>(commentService.findAll(articleId), HttpStatus.OK);
    }
}
