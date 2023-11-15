package com.woofnmeow.wnm_project_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetUserOrderProductsRespDto {
    // 사용자 주문 상품 조회
    private int oderProductsId;
    private int productDtlId;
    private int count;
    private int productMstId;
    private int price;
    private String sizeName;
    private String productName;
    private String productThumbnailUrl;
}
