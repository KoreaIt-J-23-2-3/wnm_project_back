package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.entity.OrderProducts;
import com.woofnmeow.wnm_project_back.dto.OrderProductsReqDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class AddOrderReqDto {
    private int userId;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddressNumber;
    private String shippingAddressName;
    private String shippingAddressDetailName;

    private List<OrderProductsReqDto> orderData;
    private Boolean isCart;

    public Order toOrderEntity() {
        return Order.builder()
                .userId(userId)
                .shippingName(shippingName)
                .shippingPhone(shippingPhone)
                .shippingAddressNumber(shippingAddressNumber)
                .shippingAddressName(shippingAddressName)
                .shippingAddressDetailName(shippingAddressDetailName)
                .build();
    }

    public List<OrderProducts> toOrderProductsEntity(int orderId) {
        List<OrderProducts> orderProductsList = new ArrayList<>();

        orderData.forEach(od -> {
            orderProductsList.add(OrderProducts.builder()
                    .orderId(orderId)
                    .productId(od.getProductId())
                    .count(od.getCount())
                    .size(od.getSize())
                    .build()
            );
        });
        return orderProductsList;
    }
}
