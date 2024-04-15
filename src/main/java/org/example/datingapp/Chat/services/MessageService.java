package org.example.datingapp.Chat.services;

import org.example.datingapp.Chat.entities.MessageEntity;
import org.example.datingapp.Chat.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<MessageEntity> getMessageHistory(Integer userId1, Integer userId2, int n) {
        return messageRepository.findMessageHistory(userId1, userId2, PageRequest.of(n, 20));
    }

    public MessageEntity saveMessage(MessageEntity message) {
        return messageRepository.save(message);
    }

    public List<MessageEntity> queryRealtimeMessage(Integer userId1, Integer userId2, LocalTime loginTime) {
        return messageRepository.findMessagesByUsersAndTime(userId1, userId2, loginTime);
    }
}
