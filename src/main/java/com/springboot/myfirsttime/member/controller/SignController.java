package com.springboot.myfirsttime.member.controller;

import com.springboot.myfirsttime.member.data.dto.SignInResultDto;
import com.springboot.myfirsttime.member.data.dto.SignUpResultDto;
import com.springboot.myfirsttime.member.service.SignService;
import com.springboot.myfirsttime.member.service.impl.SignServiceImpl;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sign-api")
public class SignController {
    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping(value="/sign-in")
    public SignInResultDto signIn(
         
        @ApiParam(value="아이디", required = true) @RequestParam String id,
        @ApiParam(value="패스워드", required = true) @RequestParam String password) throws RuntimeException
    {
        LOGGER.info("[sign-in] 로그인 시도 중입니다. id : {}, pw : ****",id );
        SignInResultDto signInResultDto = signService.signIn(id,password);
        if(signInResultDto.getCode() == 0){
            LOGGER.info("[sign-in] 정상 로그인되었습니다. id : {}, token : {}",id,signInResultDto.getToken());
        }
        return signInResultDto;
    }

    @PostMapping(value="/sign-up")
    public SignUpResultDto signUp(
        @ApiParam(value="아이디", required = true) @RequestParam String id,
        @ApiParam(value="패스워드", required = true) @RequestParam String password,
        @ApiParam(value="이름",required = true) @RequestParam String name,
        @ApiParam(value="권한", required = true) @RequestParam String role,
        @ApiParam(value="성별",required = true) @RequestParam Boolean gender
    ){
        LOGGER.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****,name : {}, role : {}, gender : {}");
        SignUpResultDto signUpResultDto = signService.signUp(id,password,name,role,gender);
        
        LOGGER.info("[signUp] 회원가입 완료");
        return signUpResultDto;
    }

    @GetMapping(value="/exception")
    public void exceptionTest() throws RuntimeException{
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(RuntimeException e){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.error("ExceptionHandler 호출, {},{}",e.getCause(),e.getMessage());
        Map<String,String> map = new HashMap<>();
        map.put("error type",httpStatus.getReasonPhrase());
        map.put("code","400");
        map.put("message","에러 발생");

        return new ResponseEntity<>(map, responseHeaders,httpStatus);
    }
}
