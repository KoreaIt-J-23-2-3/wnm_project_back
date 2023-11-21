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
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    public Integer addOrder(Order order);
    public Integer addOrderProducts(Map<String, Object> reqMap);
    public List<Order> selectOrders(SearchOrderVo searchOrderVo);
    public Integer deleteOrder(int orderId);
    public List<Order> selectOrdersForAdmin(SearchOrderVo searchOrderVo);
    public Integer updateOrderStatus(Map<String, Object> map);
}
