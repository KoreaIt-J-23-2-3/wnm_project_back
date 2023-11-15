package com.woofnmeow.wnm_project_back.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetUserCartRespDto {
    private int cartId;
    private int userId;

    private List<GetUserCartProductsRespDto> getUserCartProductsRespDtos;

}
