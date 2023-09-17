package com.tapusd.graphqldemo.repository;

import com.tapusd.graphqldemo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}
