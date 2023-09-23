package com.tapusd.graphqldemo.graphresolver;

import com.tapusd.graphqldemo.domain.Comment;
import com.tapusd.graphqldemo.domain.Post;
import com.tapusd.graphqldemo.dto.CommentDTO;
import com.tapusd.graphqldemo.service.CommentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommentResolver {

    private final CommentService commentService;

    public CommentResolver(CommentService commentService) {
        this.commentService = commentService;
    }

    @QueryMapping
    public List<Comment> comments() {
        return commentService.findAll();
    }

    // Resolving the comments property in the type Post in schema.graphqls file
    @SchemaMapping
    public List<Comment> comments(Post post) {
        return commentService.findAllByPostId(post.getId());
    }

    @MutationMapping
    public long addComment(@Argument Long userId,
                           @Argument Long postId,
                           @Argument String content) {
        return commentService.save(new CommentDTO(userId, postId, content));
    }
}
