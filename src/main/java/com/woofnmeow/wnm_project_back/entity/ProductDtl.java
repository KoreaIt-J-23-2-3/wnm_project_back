package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetUserCartProductsRespDto;
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
    private int productMstId;
    private int price;
    private int sizeId;
    private int actualStock;
    private int tempStock;

    private Size size;
    private ProductMst productMst;




}
