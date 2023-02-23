package com.springboot.myfirsttime.board.data.repository;

import com.springboot.myfirsttime.board.data.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>,BoardCustomRepository {
    Page<Board> findByisDeleteOrderByNoDesc(Boolean isDelete, Pageable pageable);
    Board findByisDeleteAndNo(Boolean isDelete, int no);
}
