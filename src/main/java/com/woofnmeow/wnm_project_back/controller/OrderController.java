package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.request.AddOrderReqDto;
import com.woofnmeow.wnm_project_back.dto.request.SearchOrderReqDto;
import com.woofnmeow.wnm_project_back.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    // C
    @PostMapping("/api/order")
    public ResponseEntity<?> addOrder(@RequestBody AddOrderReqDto addOrderReqDto) {
        return ResponseEntity.ok().body(orderService.addOrder(addOrderReqDto));
    }





    // R
    @GetMapping("/api/orders")
    public ResponseEntity<?> getOrdersByUserId(SearchOrderReqDto searchOrderReqDto) {
        return ResponseEntity.ok().body(orderService.getOrdersByUserId(searchOrderReqDto));
    }

    @GetMapping("/api/admin/orders")
    public ResponseEntity<?> getOrdersForAdmin(SearchOrderReqDto searchOrderReqDto) {
        return ResponseEntity.ok().body(orderService.getOrdersForAdmin(searchOrderReqDto));
    }

    @GetMapping("/api/order/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable int orderId) {

        return ResponseEntity.ok().body(orderService.getOrder(orderId));
    }

    @GetMapping("/api/order/count")
    public ResponseEntity<?> getOrderCount() {
        return ResponseEntity.ok(orderService.getOrderCount());
    }



    // U
      @PutMapping("/api/admin/order/{orderId}/{orderStatus}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable int orderId, @PathVariable int orderStatus) {
        return ResponseEntity.ok().body(orderService.updateOrderStatus(orderId, orderStatus));
    }

    @PutMapping("/api/order/{orderId}")
    public ResponseEntity<?> updateShippingConfirmation(@PathVariable int orderId) {
        return ResponseEntity.ok().body(orderService.updateOrderStatus(orderId, 3));
    }
  




    // D
    @DeleteMapping("/api/order/{orderId}")
    public ResponseEntity<?> removeOrder(@PathVariable int orderId) {
        return ResponseEntity.ok().body(orderService.removeOrder(orderId));
    }

}
