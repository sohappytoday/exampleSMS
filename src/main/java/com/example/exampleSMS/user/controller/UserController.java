package com.example.exampleSMS.user.controller;

import com.example.exampleSMS.common.response.ApiResponse;
import com.example.exampleSMS.user.dto.request.RegisterUserRequest;
import com.example.exampleSMS.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody RegisterUserRequest registerUserRequest) {

        userService.registerUser(registerUserRequest);

        return ResponseEntity.ok(ApiResponse.success("회원가입에 성공하였습니다."));
    }
}
