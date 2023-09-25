package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.Chat;
import com.tapusd.graphqldemo.dto.ChatDTO;
import com.tapusd.graphqldemo.repository.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat save(ChatDTO dto) {
        Assert.notNull(dto, "dto can not be null!");
        Assert.hasText(dto.roomId(), "Room id can not be empty!");
        Assert.hasText(dto.message(), "Message can not be empty!");
        Assert.notNull(dto.userId(), "User id can not be null!");

        var chat = new Chat()
                .setRoomId(dto.roomId())
                .setUserId(dto.userId())
                .setMessage(dto.message());

        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> findAllByRoomId(String roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }
}
