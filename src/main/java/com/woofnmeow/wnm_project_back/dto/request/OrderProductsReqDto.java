package com.woofnmeow.wnm_project_back.dto.request;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class OrderProductsReqDto {
    private int productDtlId;
    private int count;

    public Map<String, Object> toProductDtlMap(int orderId) {
        Map<String, Object> productDtlEntity = new HashMap<>();

        productDtlEntity.put("orderId", orderId);
        productDtlEntity.put("productDtlId", productDtlId);
        productDtlEntity.put("count", count);

        return productDtlEntity;
    }
}
