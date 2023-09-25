package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.Chat;
import com.tapusd.graphqldemo.dto.ChatDTO;

import java.util.List;

public interface ChatService {
    Chat save(ChatDTO dto);
    List<Chat> findAllByRoomId(String roomId);
}
