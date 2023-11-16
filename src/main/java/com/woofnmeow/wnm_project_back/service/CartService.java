package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddCartReqDto;
import com.woofnmeow.wnm_project_back.dto.GetUserCartProductsRespDto;
import com.woofnmeow.wnm_project_back.entity.Cart;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    @Transactional(rollbackFor = Exception.class)
    public Boolean addCart(int userId, List<AddCartReqDto> addCartReqDto) {

        return cartMapper.addCart(addCartReqDto.stream().map(dto -> dto.toCartProductsEntity(userId)).collect(Collectors.toList())) > 0;
    }

    public List<GetUserCartProductsRespDto> getCartProductsByUserId(int userId) {

        return cartMapper.findCartByUserId(userId).stream().map(Cart::toGetUserCartProductsRespDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteCartProduct(int cartId) {

        return cartMapper.deleteCartProduct(cartId) > 0;
    }

}
