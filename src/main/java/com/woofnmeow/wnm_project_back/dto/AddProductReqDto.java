package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Product;
import lombok.Data;

@Data
public class AddProductReqDto {
    private String productName;
    private int productPrice;
    private int productStock;
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

    public Product toEntity() {
        return Product.builder()
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .productDetailText(productDetailText)
                .productThumbnail(productThumbnail)
                .productDetailImg(productDetailImg)
                .categoryId(categoryId)
                .productSizeXs(productSizeXs)
                .productSizeS(productSizeS)
                .productSizeM(productSizeM)
                .productSizeL(productSizeL)
                .productSizeXL(productSizeXL)
                .productSizeXXL(productSizeXXL)
                .build();
    }
}
