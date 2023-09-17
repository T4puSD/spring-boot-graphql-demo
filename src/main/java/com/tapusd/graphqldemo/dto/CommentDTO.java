package com.tapusd.graphqldemo.dto;

public record CommentDTO(Long userId, Long postId, String content) {
}
