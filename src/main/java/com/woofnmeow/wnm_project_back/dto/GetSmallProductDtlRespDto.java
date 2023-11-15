package com.woofnmeow.wnm_project_back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetSmallProductDtlRespDto {
    private int productDtlId;
    private int price;
    private int sizeId;
    private String sizeName;
    private int actualStock;
    private int tempStock;
}
