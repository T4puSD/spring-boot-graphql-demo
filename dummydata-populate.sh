#!/bin/bash

## Users
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"email": "john@dummy.com", "name": "john"}'

curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"email": "amy@dummy.com", "name": "amy"}'

## Posts
curl -X POST http://localhost:8080/posts -H "Content-Type: application/json" -d '{"slug": "the-dummy-post", "title": "The Dummy Post", "description": "THis is just a dummy post", "authorId": "1"}'

curl -X POST http://localhost:8080/posts -H "Content-Type: application/json" -d '{"slug": "the-nvim-comparator", "title": "The Nvim Comparator of Future", "description": "Finally some comparator with futuristic API emerges in the NeoVim plugin repository. Really existed to try it out", "authorId": "2"}'

curl -X POST http://localhost:8080/posts -H "Content-Type: application/json" -d '{"slug": "caddy", "caddy-as-ftp-server": "Caddy As FTP Server", "description": "Caddy is a fantastic front server! I really like it for its versatility but i use it mostly for serving files over ftp in my local network and i really like it. Give it a try yourself i hop you will like it.", "authorId": "2"}'


## Comments
curl -X POST http://localhost:8080/comments -H "Content-Type: application/json" -d '{"userId": "1", "postId": "1", "content": "Great Post"}'

curl -X POST http://localhost:8080/comments -H "Content-Type: application/json" -d '{"userId": "1", "postId": "2", "content": "I doubt it!!!"}'

curl -X POST http://localhost:8080/comments -H "Content-Type: application/json" -d '{"userId": "2", "postId": "2", "content": "Indeed!!! You are right about that."}'
