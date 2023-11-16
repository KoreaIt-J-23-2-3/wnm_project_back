package com.woofnmeow.wnm_project_back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMasterProductRespDto {
    private int productMstId;
    private String productName;
    private String petTypeName;
    private String productCategoryName;
    private String productThumbnailUrl;
    private String createDate;
}
