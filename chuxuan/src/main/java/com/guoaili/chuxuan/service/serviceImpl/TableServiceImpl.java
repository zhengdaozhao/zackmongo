package com.guoaili.chuxuan.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guoaili.chuxuan.DTO.WritingUpdVo;
import com.guoaili.chuxuan.entity.WritingEntity;
import com.guoaili.chuxuan.repository.WritingRepository;
import com.guoaili.chuxuan.service.TableService;
import com.guoaili.chuxuan.service.UserService;

@Service
public class TableServiceImpl implements TableService{
    @Autowired
    private WritingRepository writingRepository;

    @Autowired
    private UserService userService;


    @Override
    public List<WritingEntity> getAllWriting(String subject) {
        List<WritingEntity> bySubject = writingRepository.findBySubject(userService.getUser().getUsername(),subject);
        return bySubject;
    }

    @Override
    public void deleteOneWriting(ObjectId id) {
        try{
            writingRepository.deleteById(id);
        }catch(Exception ex){
            throw new RuntimeException("删除记录时发生异常");
        }
    }

    @Override
    public void updateOneWriting(WritingUpdVo wuv) {
        WritingEntity zpddbz =writingRepository.findById(wuv.getId()).orElse(null);
        if (zpddbz==null){
            throw new RuntimeException("查询异常，请稍后再试");
        }
        // set the new coming data for update
        zpddbz.setModday(LocalDate.now());
        zpddbz.setSample(wuv.getSample());
        zpddbz.setTitle(wuv.getTitle());
        writingRepository.save(zpddbz);
    }

}
