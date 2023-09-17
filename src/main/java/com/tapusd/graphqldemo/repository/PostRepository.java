package com.tapusd.graphqldemo.repository;

import com.tapusd.graphqldemo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, Long> {
}
