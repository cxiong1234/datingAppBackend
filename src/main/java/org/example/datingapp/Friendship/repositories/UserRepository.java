package org.example.datingapp.Friendship.repositories;
import org.example.datingapp.Friendship.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // You can define custom query methods here
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
    boolean existsByUserId(Integer userId);
}