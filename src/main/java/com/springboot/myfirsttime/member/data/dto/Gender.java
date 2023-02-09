package com.springboot.myfirsttime.member.data.dto;

import lombok.Getter;

@Getter
public enum Gender {
    MALE(true), FEMALE(false);

    boolean gender;

    Gender(boolean gender) {
        this.gender = gender;
    }

    public boolean toBoolean(){return this.gender;}
}
