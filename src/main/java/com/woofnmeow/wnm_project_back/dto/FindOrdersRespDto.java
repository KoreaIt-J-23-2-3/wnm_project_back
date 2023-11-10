package com.woofnmeow.wnm_project_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindOrdersRespDto {
    private int orderId;
    private int userId;
    private String orderDate;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddressNumber;
    private String shippingAddressName;
    private String shippingAddressDetailName;
    private int productId;
    private String size;
    private int count;
    private String productName;
    private int productPrice;
    private String productThumbnail;
    private int petTypeId;
    private String petTypeName;
    private int productCategoryId;
    private String productCategoryName;

}
