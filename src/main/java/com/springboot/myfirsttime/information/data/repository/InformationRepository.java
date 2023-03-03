package com.springboot.myfirsttime.information.data.repository;

import com.springboot.myfirsttime.information.data.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information,Long> {
}
