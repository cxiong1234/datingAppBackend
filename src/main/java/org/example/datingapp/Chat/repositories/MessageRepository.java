package org.example.datingapp.Chat.repositories;

import org.example.datingapp.Chat.entities.MessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    @Query("SELECT m FROM MessageEntity m WHERE (m.senderUserId = ?1 AND m.receiverUserId = ?2) OR (m.senderUserId = ?2 AND m.receiverUserId = ?1) ORDER BY m.sendTime DESC")
    List<MessageEntity> findMessageHistory(Integer userId1, Integer userId2, Pageable pageable);

    @Query("SELECT m FROM MessageEntity m WHERE ((m.senderUserId = :userId1 AND m.receiverUserId = :userId2) OR (m.senderUserId = :userId2 AND m.receiverUserId = :userId1)) AND m.sendTime > :loginTime")
    List<MessageEntity> findMessagesByUsersAndTime(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2, @Param("loginTime") LocalTime loginTime);

}