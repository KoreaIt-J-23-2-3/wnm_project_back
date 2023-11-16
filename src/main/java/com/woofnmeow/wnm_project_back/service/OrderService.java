package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.*;
import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
import com.woofnmeow.wnm_project_back.repository.OrderMapper;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import com.woofnmeow.wnm_project_back.vo.DeleteOrderCartVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addOrder(AddOrderReqDto addOrderReqDto) {

            Order order = addOrderReqDto.toOrderEntity();
            orderMapper.addOrder(order);

            addOrderReqDto.getOrderProductData().forEach(productData -> {

                orderMapper.addOrderProducts(productData.toProductDtlMap(order.getOrderId()));
            });
        return true;
    }

    public List<GetUserOrdersRespDto> selectOrders(SearchOrderReqDto searchOrderReqDto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderMapper.selectOrders(searchOrderReqDto.toVo(principalUser.getUser().getUserId())).stream().map(Order::toGetUserOrdersRespDto).collect(Collectors.toList());
    }
}
