## Graphql Demo
This project is a demo project to explore `GraphQL` with spring boot and mongodb.
`REST` endpoint is also exposed for available domain entities.

### MongoDB Relational Mapping
The recommended relational mapping with spring boot and mongodb is also utilized
in this project with the help of `@DocumentReference` annotation mapping and 
handling optimized `OneToMany` type mapping with the help of `@ReadOnlyProperty`
annotation. Here is the code example:

```java
class Comment {
    @Id
    private Long id;
    private String content;
    
    @DocumentReference
    private Post post;
}

class Post {
    @Id
    private Long id;
    private String title;
    
    // @ReadOnlyProperty | Disabling linking from post -> comment 
    // and only enabling one way link from comment -> post
    @ReadOnlyProperty
    // @DocumentReference | 'post' is the name of the java Variable 
    // inside Comment class and #self is post itself
    @DocumentReference(lazy = true, lookup = "{'post' : ?#{#self.id}}")
    private List<Comment> comments;
}
```

## Run the project
### Run with Test Container
This project uses `testcontainer` dependency. So the system should have
`docker` installed and running if running with test container enabled main class
located in `test/java/com/tapusd/graphqldemo/TestGraphqldemoApplication`
Run the project from that file to run without preparing a local mongodb instance.

### Run as a normal spring boot project
The project expect a mongodb instance uri to start the project so you need to provide a mongodb
connection url before running the application.

1. First export the following variable to the system level 
```shell
# if username and password is set for the mongodb 
# then use the following connection string pattern
export SPRING_DATA_MONGODB_URI=mongodb://[username:password]@[host[:port]]/[database_name]
# else
export SPRING_DATA_MONGODB_URI=mongodb://host[:port]/[database_name]
```

2. Then run the project with `maven`
```shell
mvn clean spring-boot:run
```

### Populating MongoDB with dummy data
The source directory has a bash file with rest call to fill up the test container with 
dummy data. Run the following bash file with the following command `bash dummydata-populate.sh` to do so.

### Reference
- https://spring.io/blog/2021/11/29/spring-data-mongodb-relation-modelling
- https://dev.to/tleipzig/using-documentreference-for-relations-in-spring-boot-mongodb-34c6
