package com.woofnmeow.wnm_project_back.dto.response;

import com.woofnmeow.wnm_project_back.entity.OrderProducts;
import com.woofnmeow.wnm_project_back.entity.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetOrderDetailRespDto {
    private String orderDate;
    private int orderId;
    private int orderStatus;
    private String orderUpdateDate;
    private String shippingAddressDetailName;
    private String shippingAddressName;
    private String shippingAddressNumber;
    private String shippingName;
    private String shippingPhone;
    private int userId;

    private List<OrderProducts> orderProducts;
}
