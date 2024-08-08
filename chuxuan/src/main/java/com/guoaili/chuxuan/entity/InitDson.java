package com.guoaili.chuxuan.entity;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection ="initdson")
@NoArgsConstructor
public class InitDson {
    @Id
    private ObjectId id;

    private String username;

    private String name;

    private String chname;
    
    private String allsub;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginday;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modday;
}
