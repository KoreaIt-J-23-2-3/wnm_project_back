package com.woofnmeow.wnm_project_back.dto.response;

import com.woofnmeow.wnm_project_back.entity.ProductDtl;
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
    private int orderProductsId;
    private int productDtlId;
    private int count;

    private ProductDtl productDtl;
}
