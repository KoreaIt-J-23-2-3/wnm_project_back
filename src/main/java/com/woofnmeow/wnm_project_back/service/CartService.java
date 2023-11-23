package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddCartReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserCartProductsRespDto;
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


    // C
    @Transactional(rollbackFor = Exception.class)
    public Boolean addProductToCart(int userId, List<AddCartReqDto> addCartReqDto) {
        return addCartReqDto.stream()
                .map(dto -> dto.toCartProductsEntity(userId))
                .map(cart -> cartMapper.insertCart(cart))
                .allMatch(successCount -> successCount == 1);
    }





    // R
    public List<GetUserCartProductsRespDto> getCartByUserId(int userId) {
        return cartMapper.selectCartByUserId(userId).stream().map(Cart::toGetUserCartProductsRespDto).collect(Collectors.toList());
    }





    // U







    // D
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeProductOfCart(int cartId) {
        return cartMapper.deleteProductOfCart(cartId) > 0;
    }



}
