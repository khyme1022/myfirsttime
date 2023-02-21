package com.springboot.myfirsttime.board.data.repository;

public interface BoardCustomRepository {
    public void updateByNo(String title, String content, int boardNum);
    public void deleteByNo(int boardNum);
}
