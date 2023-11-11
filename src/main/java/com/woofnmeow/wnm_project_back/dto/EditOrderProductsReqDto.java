package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.OrderProducts;
import lombok.Data;

@Data
public class EditOrderProductsReqDto {
    private int productId;
    private int count;
    private String size;
}
