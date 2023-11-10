package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.repository.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;

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
}
