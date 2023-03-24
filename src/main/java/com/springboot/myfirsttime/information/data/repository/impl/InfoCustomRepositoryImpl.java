package com.springboot.myfirsttime.information.data.repository.impl;

import com.springboot.myfirsttime.information.data.entity.Info;
import com.springboot.myfirsttime.information.data.entity.QInfo;
import com.springboot.myfirsttime.information.data.repository.InfoCustomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class InfoCustomRepositoryImpl extends QuerydslRepositorySupport implements InfoCustomRepository {
    public InfoCustomRepositoryImpl() {
        super(Info.class);
    }


    @Override
    public void viewAdd(int boardNum) {
        QInfo qInfo = QInfo.info;
        update(qInfo)
                .set(qInfo.view, qInfo.view.add(1))
                .where(qInfo.no.eq(boardNum))
                .execute();
    }
}
