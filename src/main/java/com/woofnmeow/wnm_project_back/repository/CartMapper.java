package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    public List<Cart> findCartByUserId(int userId);

    public int deleteCartProduct(int cartId);

    public int addCart(List<Cart> cart);
}
