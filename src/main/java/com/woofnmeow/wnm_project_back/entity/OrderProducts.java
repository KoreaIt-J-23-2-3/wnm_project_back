package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.response.GetUserOrderProductsRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderProducts {
    private int orderProductsId;
    private int orderId;
    private int productDtlId;
    private int count;

    private ProductDtl productDtl;

    public GetUserOrderProductsRespDto toGetOrderProductsRespDto() {
        return GetUserOrderProductsRespDto.builder()
                .orderProductsId(orderProductsId)
                .productDtl(productDtl)
                .count(count)
                .build();
    }
}
