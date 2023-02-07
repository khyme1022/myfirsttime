package com.springboot.myfirsttime.member.service;

import com.springboot.myfirsttime.member.data.dto.SignInResultDto;
import com.springboot.myfirsttime.member.data.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;
}
