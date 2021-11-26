package com.example.demographql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demographql.domain.Author;
import com.example.demographql.domain.Post;
import com.example.demographql.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostResolver implements GraphQLResolver<Post> {

    private final AuthorRepository authorRepository;

    @Autowired
    public PostResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Post post) {
        return authorRepository.findById(post.getAuthor().getId()).orElse(null);
    }
}
