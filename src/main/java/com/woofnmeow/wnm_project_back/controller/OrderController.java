package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.EditOrderProductsReqDto;
import com.woofnmeow.wnm_project_back.dto.EditOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.EditProductReqDto;
import com.woofnmeow.wnm_project_back.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/api/order")
    public ResponseEntity<?> addOrder(@RequestBody AddOrderReqDto addOrderReqDto) {
        System.out.println(addOrderReqDto);
        return ResponseEntity.ok().body(orderService.addOrder(addOrderReqDto));
    }

    @PutMapping("/api/order/{orderId}")
    public ResponseEntity<?> editOrder(@PathVariable int orderId, @RequestParam int orderProductsId, @RequestBody EditOrderReqDto editOrderReqDto) {
        return ResponseEntity.ok().body(orderService.editOrder(orderId, orderProductsId, editOrderReqDto));
    }
}
