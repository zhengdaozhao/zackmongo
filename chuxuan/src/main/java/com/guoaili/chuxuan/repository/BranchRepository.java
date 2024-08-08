package com.guoaili.chuxuan.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.guoaili.chuxuan.entity.Branch;

@Repository
public interface BranchRepository extends MongoRepository<Branch,ObjectId> {
    List<Branch> findAll();

    void deleteByChname(String brhname);
}
