package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.GetUserOrderProductsRespDto;
import com.woofnmeow.wnm_project_back.dto.GetUserOrdersRespDto;
import com.woofnmeow.wnm_project_back.dto.OrderProductsReqDto;
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

    public List<GetUserOrdersRespDto> selectOrders(String searchOption, String value, String sort, int page) {
        Map<String, Object> reqMap = new HashMap<>();

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = principalUser.getName();

        reqMap.put("pageIndex", (page - 1) * 10);
        reqMap.put("userId", userId);
        reqMap.put("searchOption", searchOption);
        reqMap.put("searchValue", value);
        reqMap.put("sortOption", sort);

        return null;
    }
}
