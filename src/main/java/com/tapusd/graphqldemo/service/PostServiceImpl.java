package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.Post;
import com.tapusd.graphqldemo.domain.User;
import com.tapusd.graphqldemo.dto.PostDTO;
import com.tapusd.graphqldemo.repository.PostRepository;
import com.tapusd.graphqldemo.repository.sequence.DatabaseSequenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final DatabaseSequenceRepository databaseSequenceRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository,
                           DatabaseSequenceRepository databaseSequenceRepository,
                           UserService userService) {
        this.postRepository = postRepository;
        this.databaseSequenceRepository = databaseSequenceRepository;
        this.userService = userService;
    }

    @Override
    public long save(PostDTO dto) {
        var post = new Post()
                .setId(databaseSequenceRepository.getNextSequence(Post.SEQUENCE_NAME))
                .setSlug(dto.slug())
                .setTitle(dto.title())
                .setDescription(dto.description())
//                .setAuthor(userService.findById(dto.authorId()).orElseThrow(() -> new IllegalArgumentException("No author found with provided id")));
                // saving post without confirming if the author id is valid
                .setAuthor(new User().setId(dto.authorId()));
        postRepository.save(post);
        return post.getId();
    }

    @Override
    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllByAuthorId(Long id) {
        return postRepository.findAllByAuthorId(id);
    }
}
