package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddCartReqDto;
import com.woofnmeow.wnm_project_back.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @PostMapping("/api/cart/{userId}")
    public ResponseEntity<?> addToCart(@PathVariable int userId, @RequestBody List<AddCartReqDto> addCartReqDto) {
        return ResponseEntity.ok(cartService.addCart(userId, addCartReqDto));
    }

    @GetMapping("/api/cart/{userId}")
    public ResponseEntity<?> getCartProducts(@PathVariable int userId) {
        return ResponseEntity.ok(cartService.getCartProductsByUserId(userId));
    }

    @DeleteMapping("/api/cart/{cartId}")
    public ResponseEntity<?> deleteCartProduct(@PathVariable int cartId) {

        System.out.println("머니?" + cartId);
        return ResponseEntity.ok(cartService.deleteCartProduct(cartId));
    }


}
