package com.woofnmeow.wnm_project_back.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ReviewException extends RuntimeException{
    Map<String, String> errorMap = new HashMap<>();

    public ReviewException(Map<String, String> errorMap) {
        super("리뷰 오류 발생");
        this.errorMap = errorMap;
        errorMap.forEach((k, v) -> {
            System.out.println(k + ": " + v );
        });
    }
}
