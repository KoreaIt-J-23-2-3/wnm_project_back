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
    private final CartMapper cartMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addOrder(AddOrderReqDto addOrderReqDto) {

        Order order = addOrderReqDto.toOrderEntity();
        orderMapper.addOrder(order);

        addOrderReqDto.getOrderProductData().forEach(productData -> {
            orderMapper.addOrderProducts(productData.toProductDtlMap(order.getOrderId()));
        });

        if(addOrderReqDto.getIsCart()) {
            Map<String, Object> map = new HashMap<>();
            cartMapper.deleteOrderCart(DeleteOrderCartVo.builder()
                            .userId(addOrderReqDto.getUserId())
                            .products(addOrderReqDto.getOrderProductData().stream()
                                    .map(orderProduct -> orderProduct.getProductDtlId())
                                    .collect(Collectors.toList()))
                            .build());
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<GetUserOrdersRespDto> selectOrders(SearchOrderReqDto searchOrderReqDto) {
//        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(searchOrderReqDto); principalUser.getUser().getUserId()
        return orderMapper.selectOrders(searchOrderReqDto.toVo(11)).stream().map(Order::toGetUserOrdersRespDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteOrder(int orderId) {
        return orderMapper.deleteOrder(orderId) > 0;
    }
}
