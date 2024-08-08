package com.guoaili.chuxuan.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.digester.DocumentProperties.Charset;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guoaili.chuxuan.DTO.WritingDto;
import com.guoaili.chuxuan.DTO.WritingUpdVo;
import com.guoaili.chuxuan.entity.WritingEntity;
import com.guoaili.chuxuan.service.TableService;

@RestController
@RequestMapping("/table")
@CrossOrigin
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/writing")
    public ResponseEntity<List<WritingDto>> getWritingLists(@RequestParam("subject") String subject){
        List<WritingEntity> zpd=tableService.getAllWriting(subject);
        List<WritingDto> zpdading = zpd.stream()
            .map(entity -> new WritingDto(entity.getId().toHexString(), entity.getUsername(), entity.getSubject(),
                    entity.getTitle(), entity.getSample(), entity.getBeginday(), entity.getModday()))
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(zpdading);
    }

    @PostMapping("/writing/delete")
    public ResponseEntity<String> deleteOneWriting(@RequestParam("id") String id){
        ObjectId oid=new ObjectId(id);
        tableService.deleteOneWriting(oid);
        return ResponseEntity.ok().body("删除记录成功");
    }
    // 2024/7/1
    @PostMapping("/writing/update")
    public ResponseEntity<String> updateOneWriting( 
            @RequestParam("id") String id,  
            @RequestParam("title") String title,  
            @RequestParam("sample") String sample) {  
        ObjectId oi=new ObjectId(id);
        WritingUpdVo wuv=new WritingUpdVo(oi);
                wuv.setTitle(title);
                wuv.setSample(sample);
        tableService.updateOneWriting(wuv);
        return ResponseEntity.ok().body("更新记录成功");
    }

}
