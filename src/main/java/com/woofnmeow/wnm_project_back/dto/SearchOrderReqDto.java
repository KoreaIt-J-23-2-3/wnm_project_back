package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.vo.SearchOrderVo;
import lombok.Data;

@Data
public class SearchOrderReqDto {
    private String searchOption;
    private String searchValue;
    private String sortOption;


    public SearchOrderVo toVo(int userId) {
        return SearchOrderVo.builder()
                .userId(userId)
                .searchOption(searchOption)
                .searchValue(searchValue)
                .sortOption(sortOption)
                .build();
    }
}
