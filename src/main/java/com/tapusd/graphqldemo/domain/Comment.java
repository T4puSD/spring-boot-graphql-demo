package com.tapusd.graphqldemo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comment")
// One user can not comment more than once in a post
@CompoundIndex(def = "{'user_id': 1, 'post_id': 1}", unique = true)
public class Comment {

    public static final String SEQUENCE_NAME = "comment_sequence";

    @Id
    private Long id;

    @DocumentReference
    @Field(name = "user_id")
    private User user;

    @DocumentReference
    @Field(name = "post_id")
    private Post post;

    private String content;

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Comment setUser(User user) {
        this.user = user;
        return this;
    }

    public Post getPost() {
        return post;
    }

    public Comment setPost(Post post) {
        this.post = post;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                '}';
    }
}
