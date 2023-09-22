package com.tapusd.graphqldemo.repository;

import com.tapusd.graphqldemo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, Long> {
    List<Post> findAllByAuthorId(Long id);
}
