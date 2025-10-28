package com.example.exampleSMS.user.domain;

import com.example.exampleSMS.sms.domain.Sms;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "phone_number", nullable = false,  unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Sms> messages = new ArrayList<>();

    //생성 메서드
    public static User create(String username, String phoneNumber) {
        User user = new User();
        user.username = username;
        user.phoneNumber = phoneNumber;
        return user;
    }

    //연관관계 메서드
    public void addSms(Sms sms) {
        messages.add(sms);
        sms.setUser(this);
    }
}
