package com.guoaili.chuxuan.service.serviceImpl;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.guoaili.chuxuan.DTO.WritingVo;
import com.guoaili.chuxuan.entity.WritingEntity;
import com.guoaili.chuxuan.repository.WritingRepository;
import com.guoaili.chuxuan.service.FileStorageService;
import com.guoaili.chuxuan.service.UserService;


@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private WritingRepository writingRepository;


    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void uploadWriting(WritingVo wv) {
        // 2024/7/1 re write the logic
        WritingEntity zpddbz =new WritingEntity();
        String username=userService.getUser().getUsername();
        zpddbz.setBeginday(LocalDate.now());
        zpddbz.setUsername(username);
        zpddbz.setModday(LocalDate.now());
        zpddbz.setSubject(wv.getSubject());
        zpddbz.setTitle(wv.getTitle());
        zpddbz.setSample(wv.getSample());
        // at last,save to database
        writingRepository.save(zpddbz);
    }

}