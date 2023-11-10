package com.woofnmeow.wnm_project_back.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cart {
    private int cartId;
    private int userId;
    private int productId;
    private String size;
    private int count;
}
