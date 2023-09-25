package com.tapusd.graphqldemo.graphresolver;

import com.tapusd.graphqldemo.domain.Chat;
import com.tapusd.graphqldemo.dto.ChatDTO;
import com.tapusd.graphqldemo.service.ChatService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChatResolver {

    private final ChatService chatService;

    public ChatResolver(ChatService chatService) {
        this.chatService = chatService;
    }

    @QueryMapping
    public List<Chat> chats(@Argument String roomId) {
        return chatService.findAllByRoomId(roomId);
    }

    @MutationMapping
    public Chat sendChat(@Argument String roomId,
                         @Argument Long userId,
                         @Argument String message) {
        return chatService.save(new ChatDTO(roomId, userId, message));
    }
}
