package org.example.datingapp.Chat.repository;

import org.example.datingapp.Chat.entity.MessageEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface MessageRepository extends ListCrudRepository<MessageEntity, Integer> {
    List<MessageEntity> findBySenderUserId(Integer senderUserId);
    List<MessageEntity> findByReceiverUserId(Integer receiverUserId);
}
