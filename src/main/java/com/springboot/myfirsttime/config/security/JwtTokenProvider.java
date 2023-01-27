package com.springboot.myfirsttime.config.security;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final UserDetailsService userDetailsService;

    @Value("${springboot.jwt.secret}")
    private String secretKey = "secretKey";

    private final long tokenValidMillisecond = 1000L * 60 * 60;
    // 토큰 생성에 사용할 secretKey와 토큰 유효시간 설정(1000ms * 60 * 60 = 1시간)


}
