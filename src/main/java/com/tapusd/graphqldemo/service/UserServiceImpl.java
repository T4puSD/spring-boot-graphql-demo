package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.User;
import com.tapusd.graphqldemo.dto.UserDTO;
import com.tapusd.graphqldemo.repository.UserRepository;
import com.tapusd.graphqldemo.repository.sequence.DatabaseSequenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DatabaseSequenceRepository sequenceRepository;

    public UserServiceImpl(UserRepository userRepository,
                           DatabaseSequenceRepository sequenceRepository) {
        this.userRepository = userRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public long save(UserDTO dto) {
        var user = new User()
                .setId(sequenceRepository.getNextSequence(User.SEQUENCE_NAME))
                .setName(dto.name())
                .setEmail(dto.email());

        userRepository.save(user);

        return user.getId();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
