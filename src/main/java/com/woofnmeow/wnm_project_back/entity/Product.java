package com.woofnmeow.wnm_project_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    private int productId;
    private String productName;
    private int productStock;
    private int productPrice;
    private String productDetailText;
    private String productThumbnail;
    private String productDetailImg;
    private int categoryId;
    private int productSizeXs;
    private int productSizeS;
    private int productSizeM;
    private int productSizeL;
    private int productSizeXL;
    private int productSizeXXL;
}
