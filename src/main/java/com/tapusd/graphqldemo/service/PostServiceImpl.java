package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.Post;
import com.tapusd.graphqldemo.domain.User;
import com.tapusd.graphqldemo.dto.PostDTO;
import com.tapusd.graphqldemo.repository.PostRepository;
import com.tapusd.graphqldemo.repository.sequence.DatabaseSequenceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final DatabaseSequenceRepository databaseSequenceRepository;

    public PostServiceImpl(PostRepository postRepository,
                           DatabaseSequenceRepository databaseSequenceRepository) {
        this.postRepository = postRepository;
        this.databaseSequenceRepository = databaseSequenceRepository;
    }

    @Override
    public long save(PostDTO dto) {
        var post = new Post()
                .setId(databaseSequenceRepository.getNextSequence(Post.SEQUENCE_NAME))
                .setSlug(dto.slug())
                .setTitle(dto.title())
                .setDescription(dto.description())
                .setTags(dto.tags())
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

    @Override
    public Page<Post> findAll(Integer page, Integer perPage) {
        if (Objects.nonNull(page) && Objects.nonNull(perPage)) {
            return postRepository.findAll(PageRequest.of(page, perPage));
        } else {
            return postRepository.findAll(Pageable.unpaged());
        }
    }
}
