package com.woofnmeow.wnm_project_back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserCartProductsRespDto {
    private int productDtlId;
    private int count;
    private String sizeName;
    private int price;
    private int productMstId;
    private String productName;
    private String productThumbnailUrl;
}
