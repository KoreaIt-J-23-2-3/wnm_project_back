package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ValidCheckException.class)
    public ResponseEntity<?> validHandlerException(ValidCheckException validCheckException) {
        return ResponseEntity.badRequest().body(validCheckException.getErrorMap());
    }

    @ExceptionHandler(SignupException.class)
    public ResponseEntity<?> signupHandlerException(SignupException signupException) {
        return ResponseEntity.badRequest().body(signupException.getErrorMap());
    }

    @ExceptionHandler(SigninException.class)
    public ResponseEntity<?> signinHandlerException(SigninException signinException) {
        return ResponseEntity.badRequest().body(signinException.getErrorMap());
    }

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<?> reviewHandlerException(ReviewException reviewException) {
        return ResponseEntity.badRequest().body(reviewException.getErrorMap());
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<?> productHandlerException(ProductException productException) {
        return ResponseEntity.badRequest().body(productException.getErrorMap());
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<?> orderHandlerException(OrderException orderException) {
        return ResponseEntity.badRequest().body(orderException.getErrorMap());
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<?> cartHandlerException(CartException cartException) {
        return ResponseEntity.badRequest().body(cartException.getErrorMap());
    }

}
