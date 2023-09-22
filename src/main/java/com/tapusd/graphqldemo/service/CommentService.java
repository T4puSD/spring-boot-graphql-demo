package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.Comment;
import com.tapusd.graphqldemo.dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    long save(CommentDTO dto);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    List<Comment> findAllByPostId(Long id);
}
