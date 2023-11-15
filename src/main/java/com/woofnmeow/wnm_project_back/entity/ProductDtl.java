package com.woofnmeow.wnm_project_back.entity;

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

}
