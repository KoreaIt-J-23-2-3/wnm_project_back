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
    private int productDtlId;
    private int count;

    public Cart toCartEntity(int userId) {
        return Cart.builder()
                .userId(userId)
                .productDtlId(productDtlId)
                .count(count)
                .build();
    }
}
