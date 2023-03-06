package com.springboot.myfirsttime.information.data.repository.impl;

import com.springboot.myfirsttime.information.data.entity.Information;
import com.springboot.myfirsttime.information.data.entity.QInformation;
import com.springboot.myfirsttime.information.data.repository.InformationCustomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class InformationCustomRepositoryImpl extends QuerydslRepositorySupport implements InformationCustomRepository {
    public InformationCustomRepositoryImpl() {
        super(Information.class);
    }


    @Override
    public void viewAdd(int boardNum) {
        QInformation qInfo = QInformation.information;
        update(qInfo)
                .set(qInfo.view, qInfo.view.add(1))
                .where(qInfo.no.eq(boardNum))
                .execute();
    }
}
