package org.example.datingapp.Chat.services;

import org.example.datingapp.Chat.entities.RoomEntity;
import org.example.datingapp.Chat.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Optional<RoomEntity> getRoomByUserIds(Integer userId1, Integer userId2) {
        return roomRepository.findRoomByUserIds(userId1, userId2);
    }

    public RoomEntity saveRoom(RoomEntity room) {
        return roomRepository.save(room);
    }
}
