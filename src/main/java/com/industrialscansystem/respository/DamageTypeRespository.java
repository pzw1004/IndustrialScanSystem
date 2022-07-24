package com.industrialscansystem.respository;


import com.industrialscansystem.Bean.DamageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DamageTypeRespository extends JpaRepository<DamageType,Integer> {

    @Query(value = "select * from damage_type where damagetype_id=?1 ",nativeQuery = true)
    public DamageType getDamageTypeNameByDamagetypeId(Integer damagetype_id);

    @Query(value = "select * from damage_type where flag=1 ",nativeQuery = true)
    public List<DamageType> getAllVaildDamageTypeName();

}
