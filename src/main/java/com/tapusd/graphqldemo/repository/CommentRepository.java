package com.tapusd.graphqldemo.repository;

import com.tapusd.graphqldemo.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long id);
}
