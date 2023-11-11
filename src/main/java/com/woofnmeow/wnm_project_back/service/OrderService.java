package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.EditOrderProductsReqDto;
import com.woofnmeow.wnm_project_back.dto.EditOrderReqDto;
import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.entity.OrderProducts;
import com.woofnmeow.wnm_project_back.repository.OrderMapper;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addOrder(AddOrderReqDto addOrderReqDto) {

        Order order = addOrderReqDto.toOrderEntity();
        orderMapper.addOrder(order);

        addOrderReqDto.toOrderProductsEntity(order.getOrderId()).forEach(orderProduct -> {
            orderMapper.addOrderProducts(orderProduct);
        });

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean editOrder(int orderId, int orderProductsId, EditOrderReqDto editOrderReqDto) {
        Order order = editOrderReqDto.toOrderEntity(orderId);
        List<OrderProducts> orderProductsList = editOrderReqDto.toOrderProductsEntity(orderId, orderProductsId);

        orderMapper.editOrder(order);

        for (OrderProducts orderProducts : orderProductsList) {
            orderMapper.editOrderProducts(orderProducts);
        }

        return true;
    }


}
