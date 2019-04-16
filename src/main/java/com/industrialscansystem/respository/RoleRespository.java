package com.industrialscansystem.respository;

import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.Role;
import com.industrialscansystem.Bean.Usrole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRespository extends JpaRepository<Role,Integer> {


    @Query(value = "select * from role where role_role_id = ?1",nativeQuery = true)
    public Role findRoleNameByRole_role_id(Integer role_role_id);



}
