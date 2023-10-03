package com.tapusd.graphqldemo.changelog;

import com.tapusd.graphqldemo.domain.Comment;
import com.tapusd.graphqldemo.domain.Post;
import com.tapusd.graphqldemo.domain.User;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

@ChangeUnit(id="data-loader", order = "1", author = "tapusd")
public class DataLoader {

    private final MongoTemplate mongoTemplate;

    public DataLoader(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // collection will be created by spring auto index creation feature
//    @BeforeExecution
//    public void before() {
//        mongoTemplate.createCollection(User.class);
//        mongoTemplate.createCollection(Post.class);
//        mongoTemplate.createCollection(Comment.class);
//    }

//    @RollbackBeforeExecution
//    public void rollbackBefore() {
//        mongoTemplate.dropCollection(User.class);
//        mongoTemplate.dropCollection(Post.class);
//        mongoTemplate.dropCollection(Comment.class);
//    }

    @Execution
    public void changeSet() {
        // load user
        List<User> users = new ArrayList<>();
        users.add(new User()
                .setId(1L)
                .setName("Jack")
                .setEmail("jack@dummy.com"));
        users.add(new User()
                .setId(2L)
                .setName("Miller")
                .setEmail("miller@dummy.com"));
        users.forEach(mongoTemplate::save);

        // load posts
        List<Post> posts = new ArrayList<>();
        posts.add(new Post()
                        .setId(1L)
                        .setSlug("example-post-1")
                        .setTitle("Example Post 1")
                        .setDescription("This is a dummy post 1")
                        .setAuthor(new User().setId(1L))
                        .setTags(List.of("example_post", "post_example")));
        posts.add(new Post()
                        .setId(2L)
                        .setSlug("example-post-2")
                        .setTitle("Example Post 2")
                        .setDescription("This is the second dummy post")
                        .setAuthor(new User().setId(1L))
                        .setTags(List.of("example_post", "post_example")));
        posts.add(new Post()
                        .setId(3L)
                        .setSlug("example-post-3")
                        .setTitle("Example Post 3")
                        .setDescription("Writing the third one!!!")
                        .setAuthor(new User().setId(2L))
                        .setTags(List.of("post", "post_dummy")));
        posts.forEach(mongoTemplate::save);

        // load comments
        List<Comment> comments = List.of(
                new Comment()
                        .setId(1L)
                        .setContent("Great post")
                        .setPost(new Post().setId(1L))
                        .setUser(new User().setId(2L)),
                new Comment()
                        .setId(2L)
                        .setContent("Gooooood")
                        .setPost(new Post().setId(2L))
                        .setUser(new User().setId(1L))
        );
        comments.forEach(mongoTemplate::save);
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(new Query(), User.class);
        mongoTemplate.remove(new Query(), Post.class);
        mongoTemplate.remove(new Query(), Comment.class);
    }
}
