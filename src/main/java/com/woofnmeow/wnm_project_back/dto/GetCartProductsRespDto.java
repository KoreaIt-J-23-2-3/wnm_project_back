package com.woofnmeow.wnm_project_back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCartProductsRespDto {
    private int cartId;
    private int userId;
    private int productDtlId;
    private String sizeName;
    private int count;
    private String productName;
    private int Price;
    private String productThumbnail;
}
