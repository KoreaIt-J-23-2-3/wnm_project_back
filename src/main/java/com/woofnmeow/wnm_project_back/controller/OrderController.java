package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.SearchOrderReqDto;
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
        return ResponseEntity.ok().body(orderService.addOrder(addOrderReqDto));
    }

    @GetMapping("/api/orders")
    public ResponseEntity<?> findOrders(SearchOrderReqDto searchOrderReqDto) {
        System.out.println(searchOrderReqDto);
        return ResponseEntity.ok().body(orderService.selectOrders(searchOrderReqDto));
    }

    @DeleteMapping("/api/order/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable int orderId) {
        return ResponseEntity.ok().body(orderService.deleteOrder(orderId));
    }

    @GetMapping("/api/admin/orders")
    public ResponseEntity<?> getOrdersForAdmin(SearchOrderReqDto searchOrderReqDto) {
        return ResponseEntity.ok().body(orderService.getOrdersForAdmin(searchOrderReqDto));
    }

    @PutMapping("/api/admin/order/{orderId}/{orderStatus}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable int orderId, @PathVariable int orderStatus) {
        return ResponseEntity.ok().body(orderService.updateOrderStatus(orderId, orderStatus));
    }
}
