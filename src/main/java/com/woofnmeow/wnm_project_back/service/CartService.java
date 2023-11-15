package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddCartReqDto;

import com.woofnmeow.wnm_project_back.dto.GetUserCartProductsRespDto;
import com.woofnmeow.wnm_project_back.dto.GetUserCartRespDto;
import com.woofnmeow.wnm_project_back.entity.Cart;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import com.woofnmeow.wnm_project_back.security.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    @Transactional(rollbackFor = Exception.class)
    public Boolean addCart(int userId, List<AddCartReqDto> addCartReqDto) {

        Cart cart = Cart.builder()
                .userId(userId)
                .cartProducts(addCartReqDto.stream().map(AddCartReqDto::toCartProductsEntity).collect(Collectors.toList()))
                .build();
        Map
        if(cartMapper.getProductCount()) {

        }
        return cartMapper.addCart(cart) > 0;

    }

    public List<GetUserCartRespDto> getCartProductsByUserId(int userId) {

        return cartMapper.findCartByUserId(userId).stream().map(Cart::toGetUserCartRespDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteCartProduct(int cartId) {

        return cartMapper.deleteCartProduct(cartId) > 0;
    }

}
