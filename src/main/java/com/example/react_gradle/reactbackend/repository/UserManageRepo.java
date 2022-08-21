package com.example.react_gradle.reactbackend.repository;

import com.example.react_gradle.reactbackend.entity.UserTb;
import com.example.react_gradle.reactbackend.entity.UserTbId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManageRepo extends JpaRepository<UserTb, UserTbId> {
    @Query(value = "select * from User_Tb where id = :id ",nativeQuery = true)
    UserTb getUserById(String id);

    @Query(value = "select * from User_Tb where id = :id and pw = :pw",nativeQuery = true)
    boolean userCheckById(String id, String pw);
}
