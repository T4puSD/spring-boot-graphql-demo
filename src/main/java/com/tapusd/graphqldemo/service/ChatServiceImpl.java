package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.Chat;
import com.tapusd.graphqldemo.dto.ChatDTO;
import com.tapusd.graphqldemo.repository.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private FluxSink<Chat> chatHandler;
    private final Flux<Chat> chatFlux;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
        chatFlux = Flux.<Chat>create(sink -> chatHandler= sink, FluxSink.OverflowStrategy.DROP);
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
                .setMessage(dto.message())
                .setCreated(LocalDateTime.now());

        Chat savedChat = chatRepository.save(chat);
        sendChat(savedChat);
        return savedChat;
    }

    @Override
    public List<Chat> findAllByRoomId(String roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }

    @Override
    public Flux<Chat> notifyNewChat() {
        return chatFlux;
    }

    private void sendChat(Chat chat) {
        if (Objects.isNull(chatHandler)) {
            return;
        }
        chatHandler.next(chat);
    }
}
