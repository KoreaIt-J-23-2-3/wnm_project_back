package com.woofnmeow.wnm_project_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetUserOrdersRespDto {
    // 사용자 주문 조회
    private int orderId;
    private int userId;
    private String orderDate;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddressNumber;
    private String shippingAddressName;
    private String shippingAddressDetailName;
    private int orderStatus;

    private List<GetUserOrderProductsRespDto> getUserOrderProductsRespDtos;
}
