package com.guoaili.chuxuan.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.chuxuan.entity.WritingEntity;

@Repository
public interface WritingRepository extends MongoRepository<WritingEntity,ObjectId> {

    @Query("{'username':?0, 'subject':?1}")
    List<WritingEntity> findBySubject(String username,String subject);
    
    // @Query("select cdr from CardReq cdr where cdr.status = CheckResult.Pending")
    // @Query(value =  "select * from users where id in (select distinct a.user_id  FROM users_roles as a inner join roles as b on a.role_id=b.id where b.role_name <>'ADMIN')",nativeQuery = true)
    @Transactional
    void deleteById(long id);

    // @Query(value =  "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'zack' AND TABLE_NAME = 'writing'",nativeQuery = true)
    // @Query(value = "select max(id) from writing",nativeQuery = true)
    // Long findTheNextAutoIncrementId();

}
