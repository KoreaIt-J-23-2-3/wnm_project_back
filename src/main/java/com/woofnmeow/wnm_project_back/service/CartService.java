package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddCartReqDto;
import com.woofnmeow.wnm_project_back.dto.GetCartProductsRespDto;
import com.woofnmeow.wnm_project_back.entity.Cart;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    public Boolean addCart(int userId, AddCartReqDto addCartReqDto) {
        addCartReqDto.setSize(addCartReqDto.getSize().substring("productSize".length()));
        return cartMapper.addCart(addCartReqDto.toCartEntity(userId)) > 0;
    }

    public List<GetCartProductsRespDto> getCartProductsByUserId(int userId) {

        return cartMapper.findCartByUserId(userId).stream().map(Cart::toCartRespDto).collect(Collectors.toList());
    }

    public Boolean deleteCartProduct(int cartId) {

        return cartMapper.deleteCartProduct(cartId) > 0;
    }

}
