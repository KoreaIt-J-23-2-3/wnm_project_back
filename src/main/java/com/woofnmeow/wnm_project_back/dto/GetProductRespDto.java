package com.woofnmeow.wnm_project_back.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetProductRespDto {
    // product_dtl_tb
    private int productDtlId;
    private int productMstId;
    private int price;
    private int sizeId;

    // product_mst_tb
    private String productName;
    private int petTypeId;
    private int productCategoryId;
    private String productDetailText;
    private String productThumbnailUrl;
    private String productDetailUrl;
    private String createDate;

    // size_tb
    private String sizeName;

    // pet_type_tb
    private String petTypeName;

    // product_category_tb
    private String productCategoryName;

    // 실제 재고 수량
    private int actualStock;
    // 가용 재고 수량 (실제 재고 - 주문 재고)
    private int tempStock;
}
