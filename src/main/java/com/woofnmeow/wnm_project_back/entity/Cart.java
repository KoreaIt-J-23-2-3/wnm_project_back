package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.response.GetUserCartProductsRespDto;
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
    private int productDtlId;
    private int count;

    private ProductDtl productDtl;

    public GetUserCartProductsRespDto toGetUserCartProductsRespDto() {
        return GetUserCartProductsRespDto.builder()
                .cartId(cartId)
                .productDtlId(productDtlId)
                .count(count)
                .productDtl(productDtl)
                .build();
    }
}