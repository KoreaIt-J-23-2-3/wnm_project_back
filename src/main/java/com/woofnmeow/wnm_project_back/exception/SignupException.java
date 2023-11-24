package com.woofnmeow.wnm_project_back.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SignupException extends RuntimeException{
    private Map<String, String> errorMap = new HashMap<>();

    public SignupException(Map<String, String> errorMap) {
        super("회원가입 오류 발생");
        this.errorMap = errorMap;
        errorMap.forEach((k, v) -> {
            System.out.println(k + ": " + v );
        });
    }

}
