package com.tapusd.graphqldemo.graphresolver;

import com.tapusd.graphqldemo.domain.Post;
import com.tapusd.graphqldemo.service.PostService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostResolver {

    private final PostService postService;

    public PostResolver(PostService postService) {
        this.postService = postService;
    }

    @QueryMapping
    public List<Post> posts() {
        return postService.findAll();
    }

    @QueryMapping
    public Post post(@Argument Long id) {
        return postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with provided id!"));
    }
}
