package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetUserCartProductsRespDto;
import com.woofnmeow.wnm_project_back.dto.GetUserCartRespDto;
import com.woofnmeow.wnm_project_back.dto.GetUserOrdersRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Cart {
    private int cartId;
    private int userId;
    private int productDtlId;
    private int count;

    private List<CartProducts> cartProducts;

    public GetUserCartRespDto toGetUserCartRespDto() {
        return GetUserCartRespDto.builder()
                .userId(userId)
                .getUserCartProductsRespDtos(cartProducts.stream().map(CartProducts::toGetUserCartProductsRespDto).collect(Collectors.toList()))
                .build();
    }
}