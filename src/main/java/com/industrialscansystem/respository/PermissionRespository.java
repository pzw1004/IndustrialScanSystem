package com.industrialscansystem.respository;

import com.industrialscansystem.Domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface PermissionRespository extends JpaRepository<Permission,Integer> {

}
