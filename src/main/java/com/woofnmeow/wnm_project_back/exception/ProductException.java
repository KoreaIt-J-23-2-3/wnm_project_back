package com.woofnmeow.wnm_project_back.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ProductException extends RuntimeException{
    Map<String, String> errorMap = new HashMap<>();

    public ProductException(Map<String, String> errorMap) {
        super("상품관련 오류 발생");
        this.errorMap = errorMap;
        errorMap.forEach((k, v) -> {
            System.out.println(k + ": " + v );
        });
    }


}
