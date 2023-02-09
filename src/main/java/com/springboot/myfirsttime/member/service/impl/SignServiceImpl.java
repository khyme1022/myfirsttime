package com.springboot.myfirsttime.member.service.impl;

import com.springboot.myfirsttime.common.CommonResponse;
import com.springboot.myfirsttime.config.security.JwtTokenProvider;
import com.springboot.myfirsttime.member.data.dto.Gender;
import com.springboot.myfirsttime.member.data.dto.SignInResultDto;
import com.springboot.myfirsttime.member.data.dto.SignUpResultDto;
import com.springboot.myfirsttime.member.data.entity.User;
import com.springboot.myfirsttime.member.data.repository.UserRepository;
import com.springboot.myfirsttime.member.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {
    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 처리 메소드
    @Override
    public SignUpResultDto signUp(String id, String password, String name, String role, Gender gender) {
        LOGGER.info("[getSignUpResult] 회원가입 정보 전달");
        User user;
        if(role.equalsIgnoreCase("admin")){ // 권한이 관리자일 경우 회원가입
            user = User.builder()
                    .uid(id)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .gender(gender.toBoolean())
                    .build();
        }else{ // // 권한이 일반회원일 경우
            user = User.builder()
                    .uid(id)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .gender(gender.toBoolean())
                    .build();
        }

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignInResultDto();

        LOGGER.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if(!savedUser.getName().isEmpty()){
            LOGGER.info("[getSignUpResult] 정상처리 완료");
            setSuccessResult(signUpResultDto);
        }else{
            LOGGER.info("[getSignUpResult] 실패");
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }

    @Override
    public SignInResultDto signIn(String id, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] 회원 정보 요청");
        User user = userRepository.getByUid(id);
        LOGGER.info("[getSignInResult] ID : {}", user.getId());
        
        LOGGER.info("패스워드 비교 수행");
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException();

        }
        LOGGER.info("[getSignInResult] 패스워드 일치");

        LOGGER.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid()),user.getRoles()))
                .build();
        LOGGER.info("[getSignInResult] 객체에 값 주입");
        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    private void setSuccessResult(SignUpResultDto result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());

    }
    private void setFailResult(SignUpResultDto result){
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());

    }
}
