package com.example.exampleSMS.sms.service;

import com.example.exampleSMS.sms.SmsRepository;
import com.example.exampleSMS.sms.domain.Content;
import com.example.exampleSMS.sms.domain.Sms;
import com.example.exampleSMS.sms.dto.request.SmsRequest;
import com.example.exampleSMS.user.domain.User;
import com.example.exampleSMS.user.repository.UserRepository;
import com.solapi.sdk.SolapiClient;
import com.solapi.sdk.message.dto.request.MultipleDetailMessageSendingRequest;
import com.solapi.sdk.message.exception.SolapiMessageNotReceivedException;
import com.solapi.sdk.message.model.Message;
import com.solapi.sdk.message.service.DefaultMessageService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SmsService {

    private DefaultMessageService messageService;
    private final SmsRepository smsRepository;
    private final UserRepository userRepository;

    @Value("${solapi.sender}")
    private String senderNumber;

    @Value("${solapi.api-key}")
    private String apiKey;

    @Value("${solapi.api-secret}")
    private String apiSecret;

    @PostConstruct
    public void initSolapi() {
        this.messageService = SolapiClient.INSTANCE.createInstance(apiKey, apiSecret);
    }

    public void sendTestMessageToRecipients(SmsRequest smsRequest) {
        List<String> recipients = smsRequest.getRecipients();

        for (String to : smsRequest.getRecipients()) {
            Message msg = new Message();
            msg.setFrom(senderNumber);
            msg.setTo(to);
            msg.setText("안녕하세요. 메시지 실험을 하고 있습니다.");

            User receiver = userRepository.findByPhoneNumber(to)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 수신자: " + to));

            try {
                messageService.send(msg);
                Sms sms = Sms.create(receiver, Content.TEST);
                smsRepository.save(sms);
            } catch (SolapiMessageNotReceivedException e) {
                System.out.println("문자 전소 실패 : " + e.getFailedMessageList());
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
    }

}
