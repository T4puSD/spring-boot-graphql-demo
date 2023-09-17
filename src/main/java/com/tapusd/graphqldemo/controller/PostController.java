package com.tapusd.graphqldemo.controller;

import com.tapusd.graphqldemo.domain.Post;
import com.tapusd.graphqldemo.dto.PostDTO;
import com.tapusd.graphqldemo.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long savePost(@RequestBody PostDTO dto) {
        return postService.save(dto);
    }

    @GetMapping
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable Long id) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with provided id"));

        //TODO: fix stack overflow exception here with dto
        return post;
    }
}
