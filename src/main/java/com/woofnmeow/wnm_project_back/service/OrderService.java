package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.FindOrdersRespDto;
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
    private final CartMapper cartMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean addOrder(AddOrderReqDto addOrderReqDto) {
            Order order = addOrderReqDto.toOrderEntity();
            orderMapper.addOrder(order);
            addOrderReqDto.toOrderProductsEntity(order.getOrderId()).forEach(orderProduct -> {
                orderMapper.addOrderProducts(orderProduct);
            });
            if(addOrderReqDto.getIsCart()) {
                DeleteOrderCartVo deleteOrderCartVo = DeleteOrderCartVo.builder()
                        .userId(addOrderReqDto.getUserId())
                        .products(addOrderReqDto.getOrderData().stream().map(OrderProductsReqDto::toProductEntity).collect(Collectors.toList()))
                        .build();

                cartMapper.deleteOrderCart(deleteOrderCartVo);
            }
        return true;
    }

    public List<FindOrdersRespDto> selectOrders(String searchOption, String value, String sort, int page) {
        Map<String, Object> reqMap = new HashMap<>();
        List<FindOrdersRespDto> respDtoList = new ArrayList<>();

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = (Integer) principalUser.getAttributes().get("id");

        reqMap.put("pageIndex", (page - 1) * 10);
        reqMap.put("userId", userId);
        reqMap.put("searchOption", searchOption);
        reqMap.put("searchValue", value);
        reqMap.put("sortOption", sort);

        orderMapper.selectOrders(reqMap).forEach(resp -> {
            respDtoList.add(resp.toFindOrdersRespDto());
        });

        return respDtoList;
    }



}
