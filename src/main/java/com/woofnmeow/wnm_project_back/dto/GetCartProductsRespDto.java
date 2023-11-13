package com.woofnmeow.wnm_project_back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCartProductsRespDto {
    private int cartId;
    private int userId;
    private int productId;
    private String size;
    private int count;
    private String productName;
    private int productPrice;
    private String productThumbnail;


}
