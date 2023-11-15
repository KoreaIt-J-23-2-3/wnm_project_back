package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetSmallProductDtlRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDtl {
    private int productDtlId;
    private int price;
    private int sizeId;
    private String sizeName;
    private int actualStock;
    private int tempStock;

    public GetSmallProductDtlRespDto toSmallProductDtlRespDto() {
        return GetSmallProductDtlRespDto.builder()
                .productDtlId(productDtlId)
                .price(price)
                .sizeId(sizeId)
                .sizeName(sizeName)
                .actualStock(actualStock)
                .tempStock(tempStock)
                .build();
    }
}
