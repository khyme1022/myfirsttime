package com.springboot.myfirsttime.member.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/*
    CustomAuthenticationEntryPoint의 메시지를 처리하기 위한 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntryPointErrorResponse {
    private String msg;
}
