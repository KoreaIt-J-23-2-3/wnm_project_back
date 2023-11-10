package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.AddCartReqDto;
import com.woofnmeow.wnm_project_back.repository.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;
    public boolean addCart(int userId, AddCartReqDto addCartReqDto) {
        addCartReqDto.setSize(addCartReqDto.getSize().substring("productSize".length()));
        return cartMapper.addCart(addCartReqDto.toCartEntity(userId)) > 0;
    }

}
