package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.request.SearchOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserOrdersRespDto;
import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
import com.woofnmeow.wnm_project_back.repository.OrderMapper;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import com.woofnmeow.wnm_project_back.vo.DeleteOrderCartVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Order order = addOrderReqDto.toOrderEntity();
        orderMapper.insertOrder(order);

        addOrderReqDto.getOrderProductData().forEach(productData -> {
            orderMapper.insertProductsToOrder(productData.toProductDtlMap(order.getOrderId()));
        });

        if(addOrderReqDto.getIsCart()) {
            Map<String, Object> map = new HashMap<>();
            cartMapper.deleteProductOfCartWhenIsCart(DeleteOrderCartVo.builder()
                            .userId(addOrderReqDto.getUserId())
                            .products(addOrderReqDto.getOrderProductData().stream()
                                    .map(orderProduct -> orderProduct.getProductDtlId())
                                    .collect(Collectors.toList()))
                            .build());
        }
        return true;
    }






    // R
    public List<GetUserOrdersRespDto> getOrdersForAdmin(SearchOrderReqDto searchOrderReqDto) {
        return orderMapper.selectOrdersByUserId(searchOrderReqDto.toVo(0)).stream().map(Order::toGetUserOrdersRespDto).collect(Collectors.toList());
    }


    public List<GetUserOrdersRespDto> getOrdersByUserId(SearchOrderReqDto searchOrderReqDto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderMapper.selectOrdersByUserId(searchOrderReqDto.toVo(principalUser.getUser().getUserId())).stream().map(Order::toGetUserOrdersRespDto).collect(Collectors.toList());
    }







    // U
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrderStatus(int orderId, int orderStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("orderStatus", orderStatus);
        return orderMapper.updateOrderStatus(map) > 0;
    }





    // D
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeOrder(int orderId) {
        return orderMapper.deleteOrder(orderId) > 0;
    }



}
