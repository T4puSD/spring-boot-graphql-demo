package com.example.demographql.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demographql.domain.Post;
import com.example.demographql.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final PostRepository postRepository;

    @Autowired
    public Query(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getRecentPosts(int count, int offset) {
        return postRepository.getRecentPosts(count, offset);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
