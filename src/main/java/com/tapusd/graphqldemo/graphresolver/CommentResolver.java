package com.tapusd.graphqldemo.graphresolver;

import com.tapusd.graphqldemo.domain.Comment;
import com.tapusd.graphqldemo.service.CommentService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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
}
