package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Order;
import com.woofnmeow.wnm_project_back.entity.OrderProducts;
import com.woofnmeow.wnm_project_back.vo.SearchOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    // C
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    public Integer insertOrder(Order order);
    public Integer insertProductsToOrder(Map<String, Object> reqMap);

    // R
    public List<Order> selectOrdersByUserId(SearchOrderVo searchOrderVo);
    public Order selectOrder(int orderId);

    // U
    public Integer updateOrderStatus(Map<String, Object> map);

    // D
    public Integer deleteOrder(int orderId);

}
