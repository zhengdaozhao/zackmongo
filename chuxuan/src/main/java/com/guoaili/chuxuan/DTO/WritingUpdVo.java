package com.guoaili.chuxuan.DTO;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WritingUpdVo extends WritingVo {
    private ObjectId id;
    // private String delImages;
}
