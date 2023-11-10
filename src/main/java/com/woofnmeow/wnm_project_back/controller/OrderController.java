package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.FindOrdersReqDto;
import com.woofnmeow.wnm_project_back.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/order")
    public ResponseEntity<?> addOrder(@RequestBody AddOrderReqDto addOrderReqDto) {
        System.out.println(addOrderReqDto);
        return ResponseEntity.ok().body(orderService.addOrder(addOrderReqDto));
    }

    @GetMapping("/api/orders/{pageIndex}")
    public ResponseEntity<?> findOrders(@PathVariable int pageIndex, @RequestBody FindOrdersReqDto findOrdersReqDto) {
        return ResponseEntity.ok().body(orderService.selectOrders(pageIndex, findOrdersReqDto));
    }
}
