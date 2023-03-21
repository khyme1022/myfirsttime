package com.springboot.myfirsttime.information.data.repository;

import com.springboot.myfirsttime.information.data.entity.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info,Long>,InfoCustomRepository {
    Page<Info> findByisDeleteOrderByNoDesc(Boolean isDelete, Pageable pageable);

    Info findByisDeleteAndNo(Boolean isDelete, int no);

}
