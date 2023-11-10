package com.woofnmeow.wnm_project_back.dto;

import lombok.Data;

@Data
public class OrderProductsReqDto {
    private int productId;
    private int count;
    private String size;
}
