package com.guoaili.chuxuan.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
    
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WritingDto {

    private String id;
    private String username;
    private String subject;
    private String title;
    private String sample;
    private LocalDate beginday;
    private LocalDate modday;

}