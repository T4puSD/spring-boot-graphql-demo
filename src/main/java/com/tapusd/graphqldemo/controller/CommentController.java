package com.tapusd.graphqldemo.controller;

import com.tapusd.graphqldemo.domain.Comment;
import com.tapusd.graphqldemo.dto.CommentDTO;
import com.tapusd.graphqldemo.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public long saveComment(@RequestBody CommentDTO dto) {
        return commentService.save(dto);
    }

    @GetMapping
    public List<Comment> findAllComments() {
        return commentService.findAll();
    }

    @GetMapping("/{id}")
    public Comment findById(@PathVariable Long id) {
        return commentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with provided id"));
    }
}
