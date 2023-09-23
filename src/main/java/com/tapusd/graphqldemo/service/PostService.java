package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.Post;
import com.tapusd.graphqldemo.dto.PostDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {

    long save(PostDTO dto);

    Optional<Post> findById(long id);

    List<Post> findAll();

    List<Post> findAllByAuthorId(Long id);

    Page<Post> findAll(Integer page, Integer perPage);
}
