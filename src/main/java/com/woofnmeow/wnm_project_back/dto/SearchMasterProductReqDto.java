package com.woofnmeow.wnm_project_back.dto;

import lombok.Data;

@Data
public class SearchMasterProductReqDto {
    private String petTypeName;
    private String productCategoryName;
    private String sortOption;
    private String searchOption;
    private String searchValue;
    private int pageIndex;
}
