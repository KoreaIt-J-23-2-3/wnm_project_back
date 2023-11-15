package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.CartProducts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddCartReqDto {
    private int productDtlId;
    private int count;

    public CartProducts toCartProductsEntity() {
        return CartProducts.builder()
                .productDtlId(productDtlId)
                .count(count)
                .build();
    }
}
