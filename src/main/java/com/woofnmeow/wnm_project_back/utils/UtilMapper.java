package com.woofnmeow.wnm_project_back.utils;

import org.springframework.stereotype.Component;

import java.util.Map;


public interface UtilMapper {
    public Map<String, String> errorMapper(String key, String value);
}
