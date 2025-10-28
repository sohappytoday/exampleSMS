package com.example.exampleSMS.sms.domain;

import com.example.exampleSMS.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sms")
@Getter
@NoArgsConstructor
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sms_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "content")
    private Content content;

    //생성 메서드
    public static Sms create(User user, Content content) {
        Sms sms = new Sms();
        sms.user = user;
        sms.content = content;
        return sms;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
