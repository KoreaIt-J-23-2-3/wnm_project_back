package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.FindOrdersReqDto;
import com.woofnmeow.wnm_project_back.dto.FindOrdersRespDto;
import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.repository.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<FindOrdersRespDto> selectOrders(int pageIndex, FindOrdersReqDto findOrdersReqDto) {
        Map<String, Object> reqMap = new HashMap<>();
        List<FindOrdersRespDto> respDtoList = new ArrayList<>();

        reqMap.put("pageIndex", (pageIndex - 1) * 10);
        reqMap.put("userId", findOrdersReqDto.getUserId());
        reqMap.put("searchOption", findOrdersReqDto.getSearchOption());
        reqMap.put("searchValue", findOrdersReqDto.getSearchValue());
        reqMap.put("sortOption", findOrdersReqDto.getSortOption());

        orderMapper.selectOrders(reqMap).forEach(resp -> {
            respDtoList.add(resp.toFindOrdersRespDto());
        });

        return respDtoList;
    }



}
