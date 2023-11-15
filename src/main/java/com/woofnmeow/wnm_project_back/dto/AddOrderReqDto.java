package com.woofnmeow.wnm_project_back.dto;

import com.woofnmeow.wnm_project_back.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class AddOrderReqDto {
    // 배송지 정보
    private int userId;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddressNumber;
    private String shippingAddressName;
    private String shippingAddressDetailName;
    // 주문 상품 정보
    private List<OrderProductsReqDto> orderProductData;
    // 장바구니 구매, 바로 구매 구별
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
}
