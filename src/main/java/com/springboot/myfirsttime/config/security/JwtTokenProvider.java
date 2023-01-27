package com.springboot.myfirsttime.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final UserDetailsService userDetailsService;

    @Value("${springboot.jwt.secret}")
    private String secretKey = "secretKey";
    private final long tokenValidMillisecond = 1000L * 60 * 60;
    // 토큰 생성에 사용할 secretKey와 토큰 유효시간 설정(1000ms * 60 * 60 = 1시간)

    /* 빈 주입 시 init() 메서드 자동 실행
    * SecretKey를 Base64 형식으로 인코딩
    */
    @PostConstruct
    protected void init(){
        LOGGER.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        LOGGER.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    /*
    토큰 생성 메소드
     */
    public String createToken(String userUid, List<String> roles){
        LOGGER.info("[createToken] 토큰 생성 시작");
        Claims claims = Jwts.claims().setSubject(userUid);
        claims.put("roles", roles);
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();

        LOGGER.info("[createToken] 토큰 생성 완료");
        return token;
    }
    /*
        인증 성공 시 SecurityContextHolder에 저장할 Autehtication 생성하는 역할
     */
    public Authentication getAuthentication(String token){
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails Username : {}", userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
    
    /*
    token을 받아와서 token 파싱 수행 후 이름 추출
     */
    public String getUsername(String token){
        LOGGER.info("[getUsername] 토큰 기반 회원 구별 정보 추출");
        String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        LOGGER.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, info : {}", info);
        return info;
    }
    
    public String resolveToken(HttpServletRequest request){
        return null;
    }
}
