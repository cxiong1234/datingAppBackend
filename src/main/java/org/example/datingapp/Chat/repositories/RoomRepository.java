package org.example.datingapp.Chat.repositories;

import org.example.datingapp.Chat.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    @Query("SELECT r FROM RoomEntity r WHERE (r.userId1 = ?1 AND r.userId2 = ?2) OR (r.userId1 = ?2 AND r.userId2 = ?1)")
    Optional<RoomEntity> findRoomByUserIds(Integer userId1, Integer userId2);
}
