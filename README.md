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
`docker` installed and running. Go to the test container enabled main class
located in `test/java/com/tapusd/graphqldemo/TestGraphqldemoApplication` and 
run the project from that file to run without preparing a local mongodb instance.

> Keep in mind that the test container will restart everytime you restart the server. So   
> better utilize the intellij & spring devtools to restart the service by just re building
> the project. If you are using intellij idea then press (Ctrl + F9) to rebuild the project
> to get new changes or search for the rebuild option and click it from the menu.

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

Alternatively you can make a handly run script named `run.sh` like following: 
```shell
export SPRING_DATA_MONGODB_URI=mongodb://host[:port]/[database_name]
mvn clean spring-boot:run
```
And then and run the project with it with `bash run.sh` command.


## Test the graphql interactive query console
Go to the following url `http://localhost:8080/graphiql` to get the default graphiql
query console to perform query against the graphql server.

### Populating MongoDB with dummy data
The source directory has a bash file with rest call to fill up the test container with 
dummy data. Run the following bash file with the following command `bash dummydata-populate.sh` to do so.

### Populating With Dummy Graphql
The file `dummygraphql-query.graphqls` contains the some mutations to add new data through graphql.   
Copy the contents of the file and past it to the graphiql console and hit run to add new data with graphql   
mutations.

> Don't run the mutations endpoints unless untill you read and perform the Populating MongoDB with dummy data section

The source directory has a bash file with rest call to fill up the test container with 
dummy data. Run the following bash file with the following command `bash dummydata-populate.sh` to do so.

### Populating with liquibase dummy data changelog
To populate database with dummy data without testcontainer dependency,run the following   
command`mvn liquibase:update` and liquibase will populate the database with dummy data   
defined in the changelog.xml file.

### References
- https://spring.io/blog/2021/11/29/spring-data-mongodb-relation-modelling
- https://dev.to/tleipzig/using-documentreference-for-relations-in-spring-boot-mongodb-34c6
- https://foojay.io/today/exposing-your-data-using-spring-graphql/
- https://bootify.io/mongodb/document-reference-in-spring-boot-mongodb.html
