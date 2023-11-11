package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.entity.OrderProducts;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EditOrderReqDto {
    private int userId;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddressNumber;
    private String shippingAddressName;
    private String shippingAddressDetailName;
    private List<EditOrderProductsReqDto> orderData;

    public Order toOrderEntity(int orderId) {
        return Order.builder()
                .userId(userId)
                .orderId(orderId)
                .shippingName(shippingName)
                .shippingPhone(shippingPhone)
                .shippingAddressNumber(shippingAddressNumber)
                .shippingAddressName(shippingAddressName)
                .shippingAddressDetailName(shippingAddressDetailName)
                .build();
    }

    public List<OrderProducts> toOrderProductsEntity(int orderId, int orderProductsId) {
        List<OrderProducts> orderProductsList = new ArrayList<>();

        orderData.forEach(od -> {
            orderProductsList.add(OrderProducts.builder()
                    .orderProductsId(orderProductsId)
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
