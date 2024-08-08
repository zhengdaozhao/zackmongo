package com.guoaili.chuxuan.entity;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection ="subjects")
@NoArgsConstructor
public class Subject {
    @Id
    private ObjectId id;

    // @Column(nullable = false, unique = true)
    // private String name;

    private String chname;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginday;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modday;
}
