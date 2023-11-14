package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetCartProductsRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Cart {
    private int cartId;
    private int userId;
    private int productId;
    private String size;
    private int count;

    // join 변수
    private String productName;
    private int productPrice;
    private String productThumbnail;

    public GetCartProductsRespDto toCartRespDto() {
        return GetCartProductsRespDto.builder()
                .cartId(cartId)
                .userId(userId)
                .productId(productId)
                .size(size)
                .count(count)
                .productName(productName)
                .productPrice(productPrice)
                .productThumbnail(productThumbnail)
                .build();
    }
}
