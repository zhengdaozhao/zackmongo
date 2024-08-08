package com.guoaili.chuxuan.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.chuxuan.DTO.UptSubject;
import com.guoaili.chuxuan.entity.InitDson;
import com.guoaili.chuxuan.entity.Subject;
import com.guoaili.chuxuan.repository.InitDsonRepository;
import com.guoaili.chuxuan.repository.SubjectRepository;
import com.guoaili.chuxuan.service.SubjectService;
import com.guoaili.chuxuan.service.UserService;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private InitDsonRepository initDsonRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<InitDson> getInit() {
        // return initDsonRepository.findAll();
        String username=userService.getUser().getUsername();
        return initDsonRepository.findByUsername(
            userService.getUser().getUsername()
        );
    }

    @Override
    public InitDson getOneInit(String subname) {
        String username=userService.getUser().getUsername();
        InitDson byName = initDsonRepository.findByChname(username, subname);
        InitDson gal=new InitDson();
        if ( byName != null ) {
            gal=byName; 
        }else {
            gal=null;
        }
        return gal;
    }

    @Override
    @Transactional
    public String updateOneInit(UptSubject updData) {
        String username=userService.getUser().getUsername();
        InitDson xiangyan=initDsonRepository.findByChname(username,updData.getSubject());
        if(xiangyan==null) {
            // create a new record
            InitDson guoaili=new InitDson();
            guoaili.setUsername(username);
            guoaili.setChname(updData.getSubject());
            guoaili.setAllsub(updData.getAllsub());
            guoaili.setBeginday(LocalDate.now());
            guoaili.setModday(LocalDate.now());
            initDsonRepository.save(guoaili);
        } else {
            xiangyan.setAllsub(updData.getAllsub());
            xiangyan.setModday(LocalDate.now());
            initDsonRepository.save(xiangyan);
        }
        return "OK";
    }

    @Transactional
    @Override
    public void addOneSubject(String subname) {
        Subject sj=new Subject();
        sj.setBeginday(LocalDate.now());
        sj.setModday(LocalDate.now());
        sj.setChname(subname);
        subjectRepository.save(sj);
    }

    @Transactional
    @Override
    public void deleteOneSubject(String subname) {
        subjectRepository.deleteByChname(subname);
    }
    
}