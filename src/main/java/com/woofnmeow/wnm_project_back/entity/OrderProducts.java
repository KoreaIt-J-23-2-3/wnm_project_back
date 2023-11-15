package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetUserOrderProductsRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderProducts {
    // order_products_tb
    private int orderProductsId;
    private int productDtlId;
    private int count;

    // product_dtl_tb
    private int productMstId;
    private int price;
    private String sizeName;

    // product_mst_tb
    private String productName;
    private String productThumbnailUrl;


    public GetUserOrderProductsRespDto toGetUserOrderProductsRespDto() {
        return GetUserOrderProductsRespDto.builder()
                .oderProductsId(orderProductsId)
                .productDtlId(productDtlId)
                .count(count)
                .productMstId(productMstId)
                .price(price)
                .sizeName(sizeName)
                .productName(productName)
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
