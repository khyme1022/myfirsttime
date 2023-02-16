package com.springboot.myfirsttime.board.service;

import com.springboot.myfirsttime.board.data.entity.Board;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BoardService {
    void writeBoard(HttpServletRequest request);
    List<Board> showBoardList(int pageNum);
    Board showBoard(int boardNum);
    void modifyBoard(String title, String content);
    void deleteBoard(int boardNo);
}
