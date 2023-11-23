package com.woofnmeow.wnm_project_back.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AnnouncementExcption extends RuntimeException{
    Map<String, String> errorMap = new HashMap<>();

    public AnnouncementExcption(Map<String, String> errorMap) {
        super("공지사항 오류 발생");
        this.errorMap = errorMap;
        errorMap.forEach((k, v) -> {
            System.out.println(k + ": " + v );
        });
    }
}
