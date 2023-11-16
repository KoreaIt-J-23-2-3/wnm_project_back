package com.woofnmeow.wnm_project_back.vo;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@Builder
public class SearchMasterProductVo {
    private String petTypeName;
    private String productCategoryName;
    private String searchOption;
    private String searchValue;
    private String sortOption;
    private int pageIndex;
    private int limit;
}
