package com.tapusd.graphqldemo.repository;

import com.tapusd.graphqldemo.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, Long> {
}
