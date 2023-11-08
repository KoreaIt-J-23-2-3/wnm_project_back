package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.PrincipalRespDto;
import com.woofnmeow.wnm_project_back.entity.User;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/account/principal")
    public ResponseEntity<?> getPrincipal() {
        PrincipalUser principalUser =
                (PrincipalUser) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        User user = principalUser.getUser();

        PrincipalRespDto principalRespDto = user.toPrincipalRespDto();

        return ResponseEntity.ok(principalRespDto);
    }

}
