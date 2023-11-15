package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Cart;
import com.woofnmeow.wnm_project_back.vo.DeleteOrderCartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {
    public List<Cart> findCartByUserId(int userId);

    public int getProductCount(Map<String, Object> map);

    public int deleteCartProduct(int cartId);

    public int addCart(Cart cart);

    public int deleteOrderCart(DeleteOrderCartVo deleteOrderCartVo);
}
