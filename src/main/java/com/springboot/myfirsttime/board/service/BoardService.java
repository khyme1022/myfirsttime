package com.springboot.myfirsttime.board.service;

import com.springboot.myfirsttime.board.data.dto.BoardResponseDto;
import com.springboot.myfirsttime.board.data.entity.Board;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BoardService {
    void writeBoard(HttpServletRequest request);
    List<BoardResponseDto> showBoardList(int pageNum);
    BoardResponseDto showBoard(int boardNum);
    void modifyBoard(HttpServletRequest request, int boardNum);
    void deleteBoard(int boardNum);
}
