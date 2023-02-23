package com.springboot.myfirsttime.board.data.repository.Impl;

import com.springboot.myfirsttime.board.data.entity.Board;
import com.springboot.myfirsttime.board.data.entity.QBoard;
import com.springboot.myfirsttime.board.data.repository.BoardCustomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class BoardCustomRepositoryImpl extends QuerydslRepositorySupport implements BoardCustomRepository {
    public BoardCustomRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public void updateByNo(String title, String content, int boardNum) {
        QBoard qBoard = QBoard.board;
        update(qBoard)
                .set(qBoard.title, title)
                .set(qBoard.content, content)
                .where(qBoard.no.eq(boardNum))
                .execute();
    }

    @Override
    public void deleteByNo(int boardNum) {
        QBoard qBoard = QBoard.board;
        update(qBoard)
                .set(qBoard.isDelete,true)
                .where(qBoard.no.eq(boardNum))
                .execute();
    }

    @Override
    public void viewAdd(int view, int boardNum) {
        QBoard qBoard = QBoard.board;
        update(qBoard)
                .set(qBoard.view, view+1)
                .where(qBoard.no.eq(boardNum))
                .execute();
    }



}
