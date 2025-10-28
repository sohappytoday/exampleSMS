package com.example.exampleSMS.user.repository;

import com.example.exampleSMS.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
