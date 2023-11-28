package com.woofnmeow.wnm_project_back.entity;

import com.woofnmeow.wnm_project_back.dto.response.GetOrderDetailRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserOrdersForAdminResp;
import com.woofnmeow.wnm_project_back.dto.response.GetUserOrdersRespDto;
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
    private LocalDateTime orderUpdateDate;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddressNumber;
    private String shippingAddressName;
    private String shippingAddressDetailName;
    private int orderStatus;

    private List<OrderProducts> orderProducts;
    private User user;

    public GetUserOrdersRespDto toGetUserOrdersRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return GetUserOrdersRespDto.builder()
                .orderId(orderId)
                .userId(userId)
                .orderDate(orderDate.format(formatter))
                .orderUpdateDate(orderUpdateDate.format(formatter))
                .shippingName(shippingName)
                .shippingPhone(shippingPhone)
                .shippingAddressNumber(shippingAddressNumber)
                .shippingAddressName(shippingAddressName)
                .shippingAddressDetailName(shippingAddressDetailName)
                .orderStatus(orderStatus)
                .getUserOrderProductsRespDtos(orderProducts.stream().map(OrderProducts::toGetOrderProductsRespDto).collect(Collectors.toList()))
                .build();
    }

    public GetUserOrdersForAdminResp toGetUserOrdersForAdminRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return GetUserOrdersForAdminResp.builder()
                .orderId(orderId)
                .userId(userId)
                .orderDate(orderDate.format(formatter))
                .orderUpdateDate(orderUpdateDate.format(formatter))
                .shippingName(shippingName)
                .shippingPhone(shippingPhone)
                .shippingAddressNumber(shippingAddressNumber)
                .shippingAddressName(shippingAddressName)
                .shippingAddressDetailName(shippingAddressDetailName)
                .orderStatus(orderStatus)
                .build();
    }

    public GetOrderDetailRespDto toGetOrderDetailRespDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return GetOrderDetailRespDto.builder()
                .orderDate(orderDate.format(formatter))
                .orderStatus(orderStatus)
                .orderUpdateDate(orderUpdateDate.format(formatter))
                .shippingAddressNumber(shippingAddressNumber)
                .shippingName(shippingName)
                .shippingPhone(shippingPhone)
                .shippingAddressNumber(shippingAddressNumber)
                .shippingAddressName(shippingAddressName)
                .shippingAddressDetailName(shippingAddressDetailName)
                .userId(userId)
                .orderProducts(orderProducts)
                .build();
    }

}
