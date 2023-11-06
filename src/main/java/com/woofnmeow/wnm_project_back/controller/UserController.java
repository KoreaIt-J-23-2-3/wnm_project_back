package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.aop.annotation.ValidAop;
import com.woofnmeow.wnm_project_back.dto.EditUserReqDto;
import com.woofnmeow.wnm_project_back.dto.SignupReqDto;
import com.woofnmeow.wnm_project_back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @ValidAop
    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(userService.signup(signupReqDto));
    }

    @GetMapping("/api/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId) {
        return ResponseEntity.ok().body(userService.getUser(userId));
    }

    @ValidAop
    @PutMapping("/api/user/{userId}")
    public ResponseEntity<?> editUser(@PathVariable int userId, @RequestBody EditUserReqDto editUserReqDto, BindingResult bindingResult) {
        System.out.println(editUserReqDto);
        return ResponseEntity.ok().body(userService.editUser(userId, editUserReqDto));
    }

    @DeleteMapping("/api/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        return ResponseEntity.ok().body(userService.delete(userId));
    }


}
