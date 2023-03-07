package com.springboot.myfirsttime.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.myfirsttime.member.data.dto.EntryPointErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 인증 과정 중 예외 발생 시 응답 값 설정해서 response에 설정
 ObjectMapper - JSON to Java, Java to JSON 형변환 시키는 역할을 하는 객체
 */

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("[commence] 인증 실패로 response.sendError 발생");

        EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
        entryPointErrorResponse.setMsg("인증이 실패했습니다.");

        response.setStatus(401);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("인증 실패");


    }

}
