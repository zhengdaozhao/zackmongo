package com.guoaili.chuxuan.service;

import java.util.List;

import com.guoaili.chuxuan.entity.Branch;

public interface BranchService {

    List<Branch> findAllBranchs();

    void addOneBranch(String brhname);

    void deleteOneBranch(String brhname);
    
}
