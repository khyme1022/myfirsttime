package com.springboot.myfirsttime.board.service.impl;

import com.springboot.myfirsttime.board.data.entity.Board;
import com.springboot.myfirsttime.board.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BoardServiceImpl implements BoardService {
    @Override
    public void writeBoard(HttpServletRequest request) {

    }

    @Override
    public List<Board> showBoardList(int pageNum) {
        return null;
    }

    @Override
    public Board showBoard(int boardNum) {
        return null;
    }

    @Override
    public void modifyBoard(String title, String content) {

    }

    @Override
    public void deleteBoard(int boardNo) {

    }
}
