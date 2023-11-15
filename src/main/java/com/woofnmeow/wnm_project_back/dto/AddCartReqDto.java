package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddCartReqDto {
    private int productId;
    private String size;
    private int count;
    private int totalPrice;

    public Cart toCartEntity(int userId) {
        return Cart.builder()
                .productId(productId)
                .userId(userId)
                .size(size)
                .count(count)
                .build();
    }
}
