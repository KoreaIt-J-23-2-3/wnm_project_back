package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Product;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class OrderProductsReqDto {
    private int productId;
    private int count;
    private String size;

    public Map<String, Object> toProductEntity() {
        Map<String, Object> productEntity = new HashMap<String, Object>();
        productEntity.put("productId", productId);
        productEntity.put("size", size);
        return productEntity;
    }
}
