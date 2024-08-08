package com.guoaili.chuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.guoaili.chuxuan.DTO.WritingVo;
import com.guoaili.chuxuan.service.FileStorageService;


@RestController
@CrossOrigin
@RequestMapping("/localupload")
public class FilesController {

    @Autowired
    private FileStorageService storageService;

    // 2024/6/20
    @PostMapping("/baiduwenxin/writing")  
    public ResponseEntity<String> handleFileUpload(  
            @RequestParam("title") String title,  
            @RequestParam("sample") String sample,  
            @RequestParam("subject") String subject) {  
  
        WritingVo wv=new WritingVo(title, sample, subject);
        storageService.uploadWriting(wv);

        // 返回响应  
        return new ResponseEntity<>("Files uploaded successfully!", HttpStatus.OK);  
    }

}