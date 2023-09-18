#!/bin/bash

## Users
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"email": "john@dummy.com", "name": "john"}'

curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"email": "jommatech@dummy.com", "name": "jommatech"}'

## Posts
curl -X POST http://localhost:8080/posts -H "Content-Type: application/json" -d '{"slug": "the-dummy-post", "title": "The Dummy Post", "description": "THis is just a dummy post", "authorId": "1"}'

curl -X POST http://localhost:8080/posts -H "Content-Type: application/json" -d '{"slug": "the-nvim-comparator", "title": "The Nvim Comparator of Future", "description": "Finally some comparator with futurestic API emerges in the NeoVim plugin repository. Really existed to try it out", "authorId": "2"}'


## Comments
curl -X POST http://localhost:8080/comments -H "Content-Type: application/json" -d '{"userId": "1", "postId": "1", "content": "Agreed"}'

curl -X POST http://localhost:8080/comments -H "Content-Type: application/json" -d '{"userId": "1", "postId": "2", "content": "Agreed"}'

curl -X POST http://localhost:8080/comments -H "Content-Type: application/json" -d '{"userId": "2", "postId": "2", "content": "Agreed"}'
