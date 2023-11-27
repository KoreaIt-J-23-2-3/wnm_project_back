package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.request.SearchOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserOrdersRespDto;
import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.exception.OrderException;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
import com.woofnmeow.wnm_project_back.repository.OrderMapper;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import com.woofnmeow.wnm_project_back.utils.utilClass.ErrorMapper;
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
    private final ErrorMapper errorMapper;




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
            return true;
        }catch (Exception e) {
            throw new OrderException
                    (errorMapper.errorMapper("주문 오류", "주문 중 오류가 발생하였습니다."));
        }
    }






    // R
    public List<GetUserOrdersRespDto> getOrdersForAdmin(SearchOrderReqDto searchOrderReqDto) {
        try {
            return orderMapper.selectOrdersByUserId(searchOrderReqDto.toVo(0))
                    .stream()
                    .map(Order::toGetUserOrdersRespDto)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            throw new OrderException
                    (errorMapper.errorMapper("주문 오류", "주문 조회 중 오류가 발생하였습니다."));
        }
    }


    public List<GetUserOrdersRespDto> getOrdersByUserId(SearchOrderReqDto searchOrderReqDto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return orderMapper.selectOrdersByUserId(searchOrderReqDto.toVo(principalUser.getUser().getUserId()))
                    .stream()
                    .map(Order::toGetUserOrdersRespDto)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            throw new OrderException
                    (errorMapper.errorMapper("주문 오류", "주문 조회 중 오류가 발생하였습니다."));
        }
    }


    public Order getOrder(int orderId) {
        try {
            return orderMapper.selectOrder(orderId);
        }catch (Exception e) {
            throw new OrderException
                    (errorMapper.errorMapper("주문 오류", "배송 조회 중 오류가 발생하였습니다."));
        }
    }


    public int getOrderCount() {
        try {
            return orderMapper.getOrderCount();
        }catch (Exception e) {
            throw new OrderException
                    (errorMapper.errorMapper("주문 오류", "배송 조회 중 오류가 발생하였습니다."));
        }
    }





    // U
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrderStatus(int orderId, int orderStatus) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", orderId);
            map.put("orderStatus", orderStatus);
            return orderMapper.updateOrderStatus(map) > 0;
        }catch (Exception e) {
            throw new OrderException
                    (errorMapper.errorMapper("주문 오류", "배송 상태 수정 중 오류가 발생하였습니다."));
        }
    }





    // D
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeOrder(int orderId) {
        try {
            return orderMapper.deleteOrder(orderId) > 0;
        }catch (Exception e) {
            throw new OrderException
                    (errorMapper.errorMapper("주문 오류", "주문 삭제 중 오류가 발생하였습니다."));
        }
    }




}
