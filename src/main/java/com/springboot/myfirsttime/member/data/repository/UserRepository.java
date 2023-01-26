package com.springboot.myfirsttime.member.data.repository;

import com.springboot.myfirsttime.member.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getByUid(String uid);
}
