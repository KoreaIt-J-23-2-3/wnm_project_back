package com.woofnmeow.wnm_project_back.dto;

import lombok.Data;

@Data
public class FindOrdersReqDto {
    private int userId;
    private String searchOption;
    private String searchValue;
    private String sortOption;
}
