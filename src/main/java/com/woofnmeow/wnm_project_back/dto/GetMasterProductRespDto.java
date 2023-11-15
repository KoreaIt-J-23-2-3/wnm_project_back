package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.ProductDtl;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetMasterProductRespDto {
    private int productMstId;
    private String productName;
    private int petTypeId;
    private String petTypeName;
    private int productCategoryId;
    private String productCategoryName;
    private String createDate;
    private String productDetailText;
    private String productThumbnailUrl;
    private List<ProductDtl> productDetailData;

}
