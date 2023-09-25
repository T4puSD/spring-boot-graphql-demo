package com.tapusd.graphqldemo.domain;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "chat")
public class Chat {

    @Id
    @MongoId // using @MongoId to convert the Mongo ObjectId into String type
    private String id;

    @Field(name = "room_id")
    @Length(min = 4, max = 12)
    private String roomId;

    @Field(name = "user_id")
    private Long userId;

    @NotEmpty
    @Length(max = 1024)
    private String message;

    private LocalDateTime created;

    public String getId() {
        return id;
    }

    public Chat setId(String id) {
        this.id = id;
        return this;
    }

    public String getRoomId() {
        return roomId;
    }

    public Chat setRoomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Chat setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Chat setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Chat setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    @Override
    public String toString() {
        return "Chat{" +
               "id='" + id + '\'' +
               ", roomId='" + roomId + '\'' +
               ", userId=" + userId +
               ", message='" + message + '\'' +
               ", created=" + created +
               '}';
    }
}
