package com.tapusd.graphqldemo.graphresolver;

import com.tapusd.graphqldemo.domain.Post;
import com.tapusd.graphqldemo.domain.User;
import com.tapusd.graphqldemo.dto.PostDTO;
import com.tapusd.graphqldemo.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostResolver {

    private final PostService postService;

    public PostResolver(PostService postService) {
        this.postService = postService;
    }

    @QueryMapping
    public Page<Post> posts(@Argument Integer page, @Argument Integer perPage) {
        return postService.findAll(page, perPage);
    }

    @QueryMapping
    public Post post(@Argument Long id) {
        return postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with provided id!"));
    }

    @SchemaMapping
    public List<Post> posts(User user) {
        return postService.findAllByAuthorId(user.getId());
    }

    @MutationMapping
    public long addPost(@Argument String slug,
                        @Argument String title,
                        @Argument String description,
                        @Argument Long authorId,
                        @Argument List<String> tags) {
        return postService.save(new PostDTO(slug, title, description, authorId, tags));
    }
}
