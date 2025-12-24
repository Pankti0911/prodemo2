package com.example.prodemo1.repository;

import com.example.prodemo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
