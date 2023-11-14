package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddOrderReqDto;
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

    @GetMapping("/api/orders/{searchOption}")
    public ResponseEntity<?> findOrders(
            @PathVariable String searchOption,
            @RequestParam String value,
            @RequestParam String sort,
            @RequestParam int page) {
        return ResponseEntity.ok().body(orderService.selectOrders(searchOption, value, sort, page));
    }
}