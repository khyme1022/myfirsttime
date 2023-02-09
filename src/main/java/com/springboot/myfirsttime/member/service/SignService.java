package com.springboot.myfirsttime.member.service;

import com.springboot.myfirsttime.member.data.dto.Gender;
import com.springboot.myfirsttime.member.data.dto.SignInResultDto;
import com.springboot.myfirsttime.member.data.dto.SignUpResultDto;

public interface SignService {
    //회원가입 메소드
    SignUpResultDto signUp(String id, String password, String name, String role, Gender gender);
    //로그인 메소드
    SignInResultDto signIn(String id, String password) throws RuntimeException;
}
