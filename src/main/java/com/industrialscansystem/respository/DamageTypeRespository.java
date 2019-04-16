package com.industrialscansystem.respository;


import com.industrialscansystem.Bean.DamageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DamageTypeRespository extends JpaRepository<DamageType,Integer> {


}
