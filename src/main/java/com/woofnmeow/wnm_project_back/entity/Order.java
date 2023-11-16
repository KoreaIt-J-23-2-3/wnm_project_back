package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.GetUserOrderProductsRespDto;
import com.woofnmeow.wnm_project_back.dto.GetUserOrdersRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
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
    private int orderStatus;

    private List<OrderProducts> orderProducts;

    public GetUserOrdersRespDto toGetUserOrdersRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(orderProducts);
        return GetUserOrdersRespDto.builder()
                .orderId(orderId)
                .userId(userId)
                .orderDate(orderDate.format(formatter))
                .shippingName(shippingName)
                .shippingPhone(shippingPhone)
                .shippingAddressNumber(shippingAddressNumber)
                .shippingAddressName(shippingAddressName)
                .shippingAddressDetailName(shippingAddressDetailName)
                .orderStatus(orderStatus)
                .getUserOrderProductsRespDtos(orderProducts.stream().map(OrderProducts::toGetOrderProductsRespDto).collect(Collectors.toList()))
                .build();
    }
}
