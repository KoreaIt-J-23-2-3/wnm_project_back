package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.FindOrdersRespDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@Data
@Builder
public class Order {
    private int orderId;
    private int userId;
    private LocalDateTime orderDate;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddressNumber;
    private String shippingAddressName;
    private String shippingAddressDetailName;



    // join을 위한 변수들

    // order_products_tb
    private int productId;
    private String size;
    private int count;

    // product_tb
    private String productName;
    private int productPrice;
    private String productThumbnail;
    private int petTypeId;
    private String petTypeName;
    private int productCategoryId;
    private String productCategoryName;

    public FindOrdersRespDto toFindOrdersRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return FindOrdersRespDto.builder()
                .orderId(orderId)
                .userId(userId)
                .orderDate(orderDate.format(formatter))
                .shippingName(shippingName)
                .shippingPhone(shippingPhone)
                .shippingAddressNumber(shippingAddressNumber)
                .shippingAddressName(shippingAddressName)
                .shippingAddressDetailName(shippingAddressDetailName)
                .productId(productId)
                .size(size)
                .count(count)
                .productName(productName)
                .productPrice(productPrice)
                .productThumbnail(productThumbnail)
                .petTypeId(petTypeId)
                .petTypeName(petTypeName)
                .productCategoryId(productCategoryId)
                .productCategoryName(productCategoryName)
                .build();
    }
}
