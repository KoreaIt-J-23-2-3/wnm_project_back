package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.AddCartReqDto;
import com.woofnmeow.wnm_project_back.dto.GetProductRespDto;
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
    private int productPrice;
    private String productDetailText;
    private String productThumbnail;
    private String productDetailImg;
    private int petTypeId;
    private String petTypeName;
    private int productCategoryId;
    private String productCategoryName;
    private int noSize;
    private int productSizeXS;
    private int productSizeS;
    private int productSizeM;
    private int productSizeL;
    private int productSizeXL;
    private int productSizeXXL;

    public GetProductRespDto toProductRespDto() {
        return GetProductRespDto.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productDetailText(productDetailText.replaceAll("\n", "<br>"))
                .productThumbnail(productThumbnail)
                .productDetailImg(productDetailImg)
                .petTypeId(petTypeId)
                .petTypeName(petTypeName)
                .productCategoryId(productCategoryId)
                .noSize(noSize)
                .productCategoryName(productCategoryName)
                .productSizeXS(productSizeXS)
                .productSizeS(productSizeS)
                .productSizeM(productSizeM)
                .productSizeL(productSizeL)
                .productSizeXL(productSizeXL)
                .productSizeXXL(productSizeXXL)
                .build();
    }
}
