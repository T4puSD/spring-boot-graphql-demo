package com.example.demographql.repository;

import com.example.demographql.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM post LIMIT :count OFFSET :offset", nativeQuery = true)
    List<Post> getRecentPosts(int count, int offset);
}