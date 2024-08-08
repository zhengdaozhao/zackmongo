package com.guoaili.chuxuan.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Document(collection = "writing")
public class WritingEntity implements Serializable {
    @Id
    private ObjectId id;

    // the common part for all table
    private String username;
    
    private String subject;

    // private Important imp;

    private String title;
    // private String topic;

    private String sample;

    // private String comments;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginday;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modday;
    
}
