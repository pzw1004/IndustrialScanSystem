package com.industrialscansystem.respository;

import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.Usrole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsroleRespository extends JpaRepository<Usrole,Integer> {


    @Query(value = "select * from usrole where usrole_user_id = ?1",nativeQuery = true)
    public Usrole findUsroleIdByMember_role_id(Integer user_role_id);

}
