package com.woofnmeow.wnm_project_back.dto.request;

import com.woofnmeow.wnm_project_back.vo.SearchOrderVo;
import lombok.Data;

@Data
public class SearchOrderReqDto {
    private String searchOption;
    private String searchValue;
    private String sortOption;
    private int pageIndex;

    public SearchOrderVo toVo(int userId) {
        return SearchOrderVo.builder()
                .userId(userId)
                .searchOption(searchOption)
                .searchValue(searchValue)
                .sortOption(sortOption)
                .pageIndex((pageIndex - 1) * 10)
                .build();
    }
}
