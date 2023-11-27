package com.woofnmeow.wnm_project_back.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetUserOrdersForAdminResp {
    private int orderId;
    private int userId;
    private String orderDate;
    private String orderUpdateDate;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddressNumber;
    private String shippingAddressName;
    private String shippingAddressDetailName;
    private int orderStatus;
}
