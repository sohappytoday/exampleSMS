package com.example.exampleSMS.user.service;

import com.example.exampleSMS.user.domain.User;
import com.example.exampleSMS.user.dto.request.RegisterUserRequest;
import com.example.exampleSMS.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void registerUser(RegisterUserRequest registerUserRequest){
        String username = registerUserRequest.getUsername();
        String phoneNumber = registerUserRequest.getPhoneNumber();

        User user = User.create(username,phoneNumber);
        userRepository.save(user);
    }
}
