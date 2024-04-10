package org.example.datingapp.Register.repository;

import org.example.datingapp.Register.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}