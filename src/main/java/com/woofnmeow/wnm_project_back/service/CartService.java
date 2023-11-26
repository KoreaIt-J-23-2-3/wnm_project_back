package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddCartReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetUserCartProductsRespDto;
import com.woofnmeow.wnm_project_back.entity.Cart;
import com.woofnmeow.wnm_project_back.exception.CartException;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
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


    // C
    @Transactional(rollbackFor = Exception.class)
    public Boolean addProductToCart(int userId, List<AddCartReqDto> addCartReqDto) {
        boolean success = addCartReqDto.stream()
                .map(dto -> dto.toCartProductsEntity(userId))
                .map(cart -> cartMapper.insertCart(cart))
                .allMatch(successCount -> successCount == 1);
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("장바구니 오류", "상품을 장바구니에 담는 중 오류가 발생하였습니다.");
            throw new CartException(errorMap);
        }
        return success;
    }






    // R
    public List<GetUserCartProductsRespDto> getCartByUserId(int userId) {
        List<GetUserCartProductsRespDto> result = new ArrayList<>();
        try {
            result = cartMapper.selectCartByUserId(userId).stream().map(Cart::toGetUserCartProductsRespDto).collect(Collectors.toList());
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("장바구니 오류", "장바구니를 불러오는 중 오류가 발생하였습니다.");
        }
        return result;
    }





    // U







    // D
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeProductOfCart(int cartId) {
        boolean success = cartMapper.deleteProductOfCart(cartId) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("장바구니 오류", "장바구니에서 상품을 삭제하는 중 오류가 발생하였습니다.");
            throw new CartException(errorMap);
        }
        return success;
    }



}
