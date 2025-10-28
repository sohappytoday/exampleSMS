package com.example.exampleSMS.sms.controller;

import com.example.exampleSMS.common.response.ApiResponse;
import com.example.exampleSMS.sms.dto.request.SmsRequest;
import com.example.exampleSMS.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/test")
    public ResponseEntity<ApiResponse<String>> sendTestMessages(@RequestBody SmsRequest smsRequest) {
        smsService.sendTestMessageToRecipients(smsRequest);
        return ResponseEntity.ok(ApiResponse.success("테스트 메시지 전송 완료"));
    }
}
