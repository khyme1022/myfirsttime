package com.springboot.myfirsttime.information.data.repository.impl;

import com.springboot.myfirsttime.information.service.entity.Information;
import com.springboot.myfirsttime.information.data.repository.InformationCustomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class InformationCustomRepositoryImpl extends QuerydslRepositorySupport implements InformationCustomRepository {
    public InformationCustomRepositoryImpl() {
        super(Information.class);
    }


}
