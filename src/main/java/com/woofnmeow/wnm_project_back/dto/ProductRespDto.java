package com.woofnmeow.wnm_project_back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRespDto {
    private int productId;
    private String productName;
    private int productStock;
    private int productPrice;
    private String productDetailText;
    private String productThumbnail;
    private String productDetailImg;
    private int petTypeId;
    private int categoryId;

    // 나중에 카테고리 이름을 불러올 시 category_tb에 join해서 가져올 것
    // private String categoryName;

    private int productSizeXS;
    private int productSizeS;
    private int productSizeM;
    private int productSizeL;
    private int productSizeXL;
    private int productSizeXXL;
}
