package com.woofnmeow.wnm_project_back.dto.response;

import com.woofnmeow.wnm_project_back.entity.ProductDtl;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserCartProductsRespDto {
    private int cartId;
    private int productDtlId;
    private int count;

    private ProductDtl productDtl;
}
