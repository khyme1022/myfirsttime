package com.springboot.myfirsttime.board.service.impl;

import com.springboot.myfirsttime.board.data.dto.BoardRequestDto;
import com.springboot.myfirsttime.board.data.dto.BoardResponseDto;
import com.springboot.myfirsttime.board.data.entity.Board;
import com.springboot.myfirsttime.board.data.repository.BoardRepository;
import com.springboot.myfirsttime.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    BoardRepository boardRepository;
    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    //글 작성 메소드
    @Override
    public void writeBoard(HttpServletRequest request) {
        Board board = new BoardRequestDto(request).toEntity();
        boardRepository.save(board);
    }
    // 글 리스트 보여주는 메소드
    @Override
    public List<BoardResponseDto> showBoardList(HttpServletRequest request) {
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Page<Board> boardList = boardRepository.findByisDeleteOrderByNoDesc(false, PageRequest.of(pageNum,2));

        return boardList.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }
    // 글 상세내용
    @Override
    public BoardResponseDto showBoard(HttpServletRequest request) {
        int boardNum = Integer.parseInt(request.getParameter("boardNum"));
        Board board = boardRepository.findByisDeleteAndNo(false,boardNum);
        BoardResponseDto result= new BoardResponseDto(board);
        return result;
    }

    // 글 수정 메소드 request 객체를 통해 수정 가능 내용인 제목과 내용을 받아오고 글 번호 역시 받아온다.
    @Override
    public void modifyBoard(HttpServletRequest request) {
        Board updateBoard = new BoardRequestDto(request).toEntity();
        boardRepository.updateByNo(updateBoard.getTitle(),updateBoard.getContent(),updateBoard.getNo());
    }
    // 글 삭제 메소드
    @Override
    public void deleteBoard(HttpServletRequest request) {
        int boardNum = Integer.parseInt(request.getParameter("boardNum"));
        boardRepository.deleteByNo(boardNum);
    }
}
