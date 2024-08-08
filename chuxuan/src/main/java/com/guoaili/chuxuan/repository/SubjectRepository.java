package com.guoaili.chuxuan.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.guoaili.chuxuan.entity.Subject;

@Repository
public interface SubjectRepository extends MongoRepository<Subject,ObjectId> {
    List<Subject> findAll();
    void deleteByChname(String subname);
}