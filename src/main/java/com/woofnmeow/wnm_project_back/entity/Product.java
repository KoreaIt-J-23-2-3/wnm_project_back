package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.ProductRespDto;
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
    private int petTypeId;
    private int categoryId;
//  추후 join 시 사용  private String categoryName;
    private int productSizeXS;
    private int productSizeS;
    private int productSizeM;
    private int productSizeL;
    private int productSizeXL;
    private int productSizeXXL;

    public ProductRespDto toProductRespDto() {
        return ProductRespDto.builder()
                .productId(productId)
                .productName(productName)
                .productStock(productStock)
                .productPrice(productPrice)
                .productDetailText(productDetailText)
                .productThumbnail(productThumbnail)
                .productDetailImg(productDetailImg)
                .petTypeId(petTypeId)
                .categoryId(categoryId)
                // .categoryName(categoryName)
                .productSizeXS(productSizeXS)
                .productSizeS(productSizeS)
                .productSizeM(productSizeM)
                .productSizeL(productSizeL)
                .productSizeXL(productSizeXL)
                .productSizeXXL(productSizeXXL)
                .build();
    }

}
