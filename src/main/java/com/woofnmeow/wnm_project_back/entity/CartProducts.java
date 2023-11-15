package com.woofnmeow.wnm_project_back.entity;


import com.woofnmeow.wnm_project_back.dto.GetUserCartProductsRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartProducts {
    private int productDtlId;
    private int count;
    private int sizeId;
    private String sizeName;
    private int price;
    private int productMstId;
    private String productName;
    private String productThumbnailUrl;

    public GetUserCartProductsRespDto toGetUserCartProductsRespDto() {
        return GetUserCartProductsRespDto.builder()
                .productDtlId(this.productDtlId)
                .count(count)
                .sizeName(sizeName)
                .price(price)
                .productMstId(productMstId)
                .productName(productName)
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
