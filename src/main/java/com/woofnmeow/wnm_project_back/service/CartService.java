package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddCartReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserCartProductsRespDto;
import com.woofnmeow.wnm_project_back.entity.Cart;
import com.woofnmeow.wnm_project_back.exception.CartException;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
import com.woofnmeow.wnm_project_back.utils.utilClass.ErrorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;
    private final ErrorMapper errorMapper;


    // C
    @Transactional(rollbackFor = Exception.class)
    public Boolean addProductToCart(int userId, List<AddCartReqDto> addCartReqDto) {
        try {
            return addCartReqDto.stream()
                    .map(dto -> dto.toCartProductsEntity(userId))
                    .map(cart -> cartMapper.insertCart(cart))
                    .allMatch(successCount -> successCount == 1);
        }catch (Exception e) {
            throw new CartException
                    (errorMapper.errorMapper("장바구니 오류", "상품을 장바구니에 담는 중 오류가 발생하였습니다."));
        }
    }






    // R
    public List<GetUserCartProductsRespDto> getCartByUserId(int userId) {
        try {
            return cartMapper.selectCartByUserId(userId)
                    .stream()
                    .map(Cart::toGetUserCartProductsRespDto)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            throw new CartException
                    (errorMapper.errorMapper("장바구니 오류", "장바구니를 불러오는 중 오류가 발생하였습니다."));
        }
    }





    // U







    // D
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeProductOfCart(int cartId) {
        try {
            return cartMapper.deleteProductOfCart(cartId) > 0;
        }catch (Exception e) {
            throw new CartException
                    (errorMapper.errorMapper("장바구니 오류", "장바구니에서 상품을 삭제하는 중 오류가 발생하였습니다."));
        }
    }





}
