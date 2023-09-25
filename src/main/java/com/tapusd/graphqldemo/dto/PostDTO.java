package com.tapusd.graphqldemo.dto;

import java.util.List;

public record PostDTO(String slug, String title, String description, Long authorId, List<String> tags) {
}
