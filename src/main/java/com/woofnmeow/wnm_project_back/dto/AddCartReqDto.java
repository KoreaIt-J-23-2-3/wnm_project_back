package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Cart;
import com.woofnmeow.wnm_project_back.entity.Product;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddCartReqDto {
    private int productId;
    private String size;
    private int count;

    public Cart toCartEntity(int userId) {
        return Cart.builder()
                .productId(productId)
                .userId(userId)
                .size(size)
                .count(count)
                .build();
    }
}
