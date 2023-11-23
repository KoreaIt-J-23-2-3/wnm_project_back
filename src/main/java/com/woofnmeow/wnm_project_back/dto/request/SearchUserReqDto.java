package com.woofnmeow.wnm_project_back.dto.request;

import com.woofnmeow.wnm_project_back.vo.SearchOrderVo;
import lombok.Data;

@Data
public class SearchUserReqDto {
    private String searchOption;
    private String searchValue;

    public SearchOrderVo toVo() {
        return SearchOrderVo.builder()
                .searchOption(searchOption)
                .searchValue(searchValue)
                .build();
    }
}
