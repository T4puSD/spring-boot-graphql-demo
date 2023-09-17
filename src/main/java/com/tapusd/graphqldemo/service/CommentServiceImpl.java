package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.Comment;
import com.tapusd.graphqldemo.domain.Post;
import com.tapusd.graphqldemo.domain.User;
import com.tapusd.graphqldemo.dto.CommentDTO;
import com.tapusd.graphqldemo.repository.CommentRepository;
import com.tapusd.graphqldemo.repository.sequence.DatabaseSequenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final DatabaseSequenceRepository sequenceRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              DatabaseSequenceRepository sequenceRepository) {
        this.commentRepository = commentRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public long save(CommentDTO dto) {
        var comment = new Comment()
                .setId(sequenceRepository.getNextSequence(Comment.SEQUENCE_NAME))
                .setContent(dto.content())
                .setUser(new User().setId(dto.userId()))
                .setPost(new Post().setId(dto.postId()));

        commentRepository.save(comment);
        return comment.getId();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
