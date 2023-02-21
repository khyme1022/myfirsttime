package com.springboot.myfirsttime.board.controller;

import com.springboot.myfirsttime.board.data.dto.BoardResponseDto;
import com.springboot.myfirsttime.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/board-api")
public class BoardController {
    private final BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 글 작성 메소드
     * @param request "글 내용"
     * @return  ResponseEntity<String> "작성완료"
     */
    @PostMapping("/write")
    public ResponseEntity<String> writeBoard(HttpServletRequest request){
        boardService.writeBoard(request);
        return ResponseEntity.status(HttpStatus.OK).body("작성 완료");
    }
    /**
     * 글 리스트 출력 메소드
     * @param pageNum 페이지 번호
     * @return  ResponseEntity<List<BoardResponseDto>> 글 목록
     * /board?page={pageNum} 혹은 /board로 GET요청 시 해당 페이지를 보여줌
     */
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> readBoardList(
            @RequestParam(value = "page",required = false, defaultValue = "1") int pageNum){
        return ResponseEntity.ok(boardService.showBoardList(pageNum-1));
    }
    /**
     * 글 출력 메소드
     * @param boardNum 글 번호
     * @return  ResponseEntity<BoardResponseDto> 글 내용
     * /board/2로 GET 요청 시 해당 글번호에 해당하는 글을 보여줌
     */
    @GetMapping("/{boardNum}")
    public ResponseEntity<BoardResponseDto> readBoard(
            @PathVariable("boardNum") int boardNum
    ){
        return ResponseEntity.ok(boardService.showBoard(boardNum));
    }
    /**
     * 글 수정 메소드
     * @param request 글 수정 내용(제목, 내용)
     *        boardNum 글번호
     * @return String
     * /board/2로 PUT 요청 시 글번호와 request 요청을 받아 글을 수정함
     *
     */
    @Transactional
    @PutMapping("/{boardNum}")
    public ResponseEntity<String> updateBoard(
            HttpServletRequest request,
            @PathVariable("boardNum") int boardNum){
        boardService.modifyBoard(request,boardNum);
        return ResponseEntity.status(HttpStatus.OK).body("수정 완료");
    }
    /**
     * 글 삭제 메소드
     * @param boardNum 글번호
     * @return String "삭제 완료
     */
    @Transactional
    @DeleteMapping("/{boardNum}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardNum") int boardNum)
    {
        boardService.deleteBoard(boardNum);
        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");

    }
}
