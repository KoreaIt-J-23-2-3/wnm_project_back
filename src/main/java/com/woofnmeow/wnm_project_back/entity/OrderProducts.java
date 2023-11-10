package com.woofnmeow.wnm_project_back.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderProducts {
    private int orderProductsId;
    private int orderId;
    private int productId;
    private int count;
    private String size;
}
