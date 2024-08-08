package com.guoaili.chuxuan.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.guoaili.chuxuan.DTO.WritingUpdVo;
import com.guoaili.chuxuan.entity.WritingEntity;

public interface TableService {

    List<WritingEntity> getAllWriting(String subject);

    void deleteOneWriting(ObjectId id);
    // 2024/7/1
    void updateOneWriting(WritingUpdVo wuv);
}
