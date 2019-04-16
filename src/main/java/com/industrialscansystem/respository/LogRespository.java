package com.industrialscansystem.respository;


import com.industrialscansystem.Bean.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface LogRespository extends JpaRepository<Log,Integer> {


    //根据选择页数与关键字进行模糊搜索
    @Query(value = "select * from log where log_operation like CONCAT('%',?1,'%') or log_operator like CONCAT('%',?1,'%')  order by log_time desc limit ?2,?3 ",nativeQuery = true)
    public List<Log> findLogListByKeyAndPageNumAndNum(String key, Integer tiaozhuanPageNum, Integer num);

    @Query(value = "select  COUNT(*) from log  where log_operation like CONCAT('%',?1,'%')",nativeQuery = true)
    public Integer getLogTotalPageNumByKey(String key);


}
