package com.springboot.myfirsttime.information.data.repository;

import com.springboot.myfirsttime.information.data.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info,Long> {
}
