package com.example.exampleSMS.sms;

import com.example.exampleSMS.sms.domain.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<Sms,Long> {
}
