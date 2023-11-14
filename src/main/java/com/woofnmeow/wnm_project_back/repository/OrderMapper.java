package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.entity.OrderProducts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    public Integer addOrder(Order order);

    public Integer addOrderProducts(OrderProducts orderProducts);
    public List<Order> selectOrders(Map<String, Object> reqMap);
}