package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Cart;
import com.woofnmeow.wnm_project_back.vo.DeleteOrderCartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {
    // C
    public int insertCart(Cart cart);

    // R
    public List<Cart> selectCartByUserId(int userId);

    // U


    // D
    public int deleteProductOfCart(int cartId);
    public int deleteProductOfCartWhenIsCart(DeleteOrderCartVo deleteOrderCartVo);
}
