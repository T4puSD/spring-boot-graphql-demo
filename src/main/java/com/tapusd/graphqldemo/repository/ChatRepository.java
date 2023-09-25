package com.tapusd.graphqldemo.repository;

import com.tapusd.graphqldemo.domain.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Chat, String> {
    List<Chat> findAllByRoomId(String roomId);
}
