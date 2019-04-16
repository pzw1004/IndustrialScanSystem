package com.industrialscansystem.respository;

import com.industrialscansystem.Bean.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface MemberRespository extends JpaRepository<Member,Integer> {

    //接口内写Dao包内需要方法，主要是自定义Query数据库处理语句

    @Query(value = "select * from member where member_name = ?1 limit ?2,?3 ",nativeQuery = true)
    public List<Member> getByMemberBefore(String key, Integer tiao,Integer num);

    @Query(value = "select * from member where member_name = ?1 ",nativeQuery = true)
    public Integer getByMemberBeforezys(String key);

    @Query(value = "select u.*,r.role_namefrom member u " +
            "LEFT JOIN usrole sru on u.member_id = sru.usrole_id " +
            "LEFT JOIN role r on sru.usrole_id = r.role_id where u.member_username = ?1 ",nativeQuery = true)
    public Member findByUserName(String member_name);

    @Query(value = "select * from member where (member_username = ?1 && member_password = ?2)",nativeQuery = true)
    public Member findMemberUserMsg(String member_username,String member_password);

    @Query(value = "select * from member where member_username = ?1",nativeQuery = true)
    public Member findMemberByMember_username(String username);

    @Modifying
    @Query(value = "delete from member where member_id = ?1" ,nativeQuery = true)
    public void deleteMemberById(Integer member_id);

    @Query(value = "select * from member where (member_username = ?1 )",nativeQuery = true)
    public Member findMemberRoleMsg(String member_username);


}
