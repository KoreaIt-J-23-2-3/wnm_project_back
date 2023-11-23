package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.request.AddCartReqDto;
import com.woofnmeow.wnm_project_back.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // C
    @PostMapping("/api/cart/{userId}")
    public ResponseEntity<?> addProductToCart(@PathVariable int userId, @RequestBody List<AddCartReqDto> addCartReqDto) {
        return ResponseEntity.ok(cartService.addProductToCart(userId, addCartReqDto));
    }




    // R
    @GetMapping("/api/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }




    // U





    // D
    @DeleteMapping("/api/cart/{cartId}")
    public ResponseEntity<?> removeProductOfCart(@PathVariable int cartId) {
        return ResponseEntity.ok(cartService.removeProductOfCart(cartId));
    }


}
