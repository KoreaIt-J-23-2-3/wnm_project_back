package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.vo.SearchMasterProductVo;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchMasterProductReqDto {
    private String petTypeName;
    private String productCategoryName;
    private String searchOption;
    private String searchValue;
    private String sortOption;
    private int pageIndex;

    public SearchMasterProductVo toVo() {
        return SearchMasterProductVo.builder()
                .petTypeName(petTypeName)
                .productCategoryName(productCategoryName)
                .searchOption(searchOption)
                .searchValue(searchValue)
                .sortOption(sortOption)
                .pageIndex((pageIndex - 1) * 10)
                .limit(10)
                .build();
    }

    public SearchMasterProductVo toSearchProduct() {
        return SearchMasterProductVo.builder()
                .petTypeName(petTypeName)
                .productCategoryName(productCategoryName)
                .searchOption(searchOption)
                .searchValue(searchValue)
                .sortOption(sortOption)
                .pageIndex((pageIndex - 1) * 10)
                .limit(9)
                .build();
    }
}
