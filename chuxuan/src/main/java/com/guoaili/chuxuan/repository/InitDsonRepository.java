package com.guoaili.chuxuan.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.guoaili.chuxuan.entity.InitDson;

@Repository
public interface InitDsonRepository extends MongoRepository<InitDson,ObjectId> {

    @Query("{'username':?0 , 'chname':?1}")
    InitDson findByChname(String username, String subname2);

    List<InitDson> findByUsername(String username);
    
}
