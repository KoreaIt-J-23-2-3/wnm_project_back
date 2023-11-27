package com.woofnmeow.wnm_project_back.utils.utilClass;

import com.woofnmeow.wnm_project_back.utils.UtilMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class ErrorMapper implements UtilMapper {
    @Override
    public Map<String, String> errorMapper(String key, String value) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(key, value);
        return errorMap;
    }



}

