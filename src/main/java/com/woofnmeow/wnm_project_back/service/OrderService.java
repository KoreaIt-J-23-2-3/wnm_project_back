package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.request.SearchOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserOrdersRespDto;
import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.exception.OrderException;
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




    // C
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrder(AddOrderReqDto addOrderReqDto) {
        try {
            Order order = addOrderReqDto.toOrderEntity();
            orderMapper.insertOrder(order);
            addOrderReqDto.getOrderProductData().forEach(productData -> {
                orderMapper.insertProductsToOrder(productData.toProductDtlMap(order.getOrderId()));
            });

            if(addOrderReqDto.getIsCart()) {
                cartMapper.deleteProductOfCartWhenIsCart(DeleteOrderCartVo.builder()
                        .userId(addOrderReqDto.getUserId())
                        .products(addOrderReqDto.getOrderProductData().stream()
                                .map(orderProduct -> orderProduct.getProductDtlId())
                                .collect(Collectors.toList()))
                        .build());
            }
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("주문 오류", "주문 중 오류가 발생하였습니다.");
        }
        return true;
    }






    // R
    public List<GetUserOrdersRespDto> getOrdersForAdmin(SearchOrderReqDto searchOrderReqDto) {
        List<GetUserOrdersRespDto> result = new ArrayList<>();
        try {
            result = orderMapper.selectOrdersByUserId(searchOrderReqDto.toVo(0)).stream().map(Order::toGetUserOrdersRespDto).collect(Collectors.toList());
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("주문 오류", "주문 조회 중 오류가 발생하였습니다.");
            throw new OrderException(errorMap);
        }
        return result;
    }


    public List<GetUserOrdersRespDto> getOrdersByUserId(SearchOrderReqDto searchOrderReqDto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<GetUserOrdersRespDto> result = new ArrayList<>();
        try {
            result = orderMapper.selectOrdersByUserId(searchOrderReqDto.toVo(principalUser.getUser().getUserId())).stream().map(Order::toGetUserOrdersRespDto).collect(Collectors.toList());
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("주문 오류", "주문 조회 중 오류가 발생하였습니다.");
            throw new OrderException(errorMap);
        }
        return result;
    }

    public Order getOrder(int orderId) {
        return orderMapper.selectOrder(orderId);
    }

    public int getOrderCount() {
        return orderMapper.getOrderCount();
    }





    // U
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrderStatus(int orderId, int orderStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("orderStatus", orderStatus);
        boolean success = true;
        try {
            success = orderMapper.updateOrderStatus(map) > 0;
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("주문 오류", "배송 상태 수정 중 오류가 발생하였습니다.");
            throw new OrderException(errorMap);
        }
        return success;
    }





    // D
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeOrder(int orderId) {
        boolean success = orderMapper.deleteOrder(orderId) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("주문 오류", "주문 삭제 중 오류가 발생하였습니다.");
            throw new OrderException(errorMap);
        }
        return success;
    }



}
