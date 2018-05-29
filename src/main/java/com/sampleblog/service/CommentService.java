package com.sampleblog.service;

import com.sampleblog.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll(Long articleId);

    Comment save(Comment comment);

}
