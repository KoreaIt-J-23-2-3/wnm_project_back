package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddCartReqDto;
import com.woofnmeow.wnm_project_back.dto.GetCartProductsRespDto;

import com.woofnmeow.wnm_project_back.entity.Cart;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    public boolean addCart(int userId, List<AddCartReqDto> addCartReqDto) {
        List<Cart> cartList = new ArrayList<>();
        addCartReqDto.forEach(product -> {
            cartList.add(product.toCartEntity(userId));
        });

        return cartMapper.addCart(cartList) > 0;
    }

    public List<GetCartProductsRespDto> getCartProductsByUserId(int userId) {

        return cartMapper.findCartByUserId(userId).stream().map(Cart::toCartRespDto).collect(Collectors.toList());
    }

    public Boolean deleteCartProduct(int cartId) {

        return cartMapper.deleteCartProduct(cartId) > 0;
    }

}
