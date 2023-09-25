package com.tapusd.graphqldemo.domain;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "post")
public class Post {

    public static final String SEQUENCE_NAME = "post_sequence";

    @Id
    private Long id;

    @NotEmpty(message = "Post unique slug can not be empty")
    @Indexed(unique = true)
    private String slug;

    @NotEmpty(message = "Post title can not be empty")
    private String title;

    @NotEmpty(message = "Post description can not be empty")
    private String description;

    @Field(name = "author_id")
    @DocumentReference
    private User author;

    //@ReadOnlyProperty | Disabling linking from post -> comment and only enabling one way link from comment -> post
    @ReadOnlyProperty
    //@DocumentReference | 'post' is the name of the java Variable inside Comment class and #self is post itself
    @DocumentReference(lazy = true, lookup = "{'post' : ?#{#self.id}}")
    private List<Comment> comments;

    private List<String> tags;

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Post setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Post setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Post setAuthor(User author) {
        this.author = author;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Post setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public Post setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        return "Post{" +
               "id=" + id +
               ", slug='" + slug + '\'' +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", tags=" + tags +
               '}';
    }
}
