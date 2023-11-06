package com.woofnmeow.wnm_project_back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRespDto {
    private int productId;
    private String productName;
    private int productPrice;
    private String productDetailText;
    private String productThumbnail;
    private String productDetailImg;
    private int petTypeId;
    private int productCategoryId;
    private String productCategoryName;
    private int noSize;
    private int productSizeXS;
    private int productSizeS;
    private int productSizeM;
    private int productSizeL;
    private int productSizeXL;
    private int productSizeXXL;
}
