package com.guoaili.chuxuan.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.guoaili.chuxuan.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByUsername(String username);
}
