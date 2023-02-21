package com.springboot.myfirsttime.board.data.repository;

public interface BoardCustomRepository {
    public void updateByNo(String title, String content, int number);
    public void updateByNo(boolean isDelete);
    public void deleteByNo(int number);
}
