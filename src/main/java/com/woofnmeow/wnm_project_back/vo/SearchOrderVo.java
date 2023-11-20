package com.woofnmeow.wnm_project_back.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchOrderVo {
    private int userId;
    private String searchOption;
    private String searchValue;
    private String sortOption;
}
