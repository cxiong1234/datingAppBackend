package org.example.datingapp.Chat;

import org.example.datingapp.Chat.entities.MessageEntity;
import org.example.datingapp.Chat.entities.RoomEntity;
import org.example.datingapp.Chat.services.MessageService;
import org.example.datingapp.Chat.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatRESTController {

    private final MessageService messageService;
    private final RoomService roomService;

    @Autowired
    public ChatRESTController(MessageService messageService, RoomService roomService){
        this.messageService = messageService;
        this.roomService = roomService;
    }

    @PostMapping("/sendMessage")
    public MessageEntity sendMessage(@RequestBody MessageEntity message){
        return messageService.saveMessage(message);
    }

    @GetMapping("/refreshMessage")
    public List<MessageEntity> refreshMessage(@RequestParam(name = "userId1") Integer userId1,
                                              @RequestParam(name = "userId2") Integer userId2,
                                              @RequestParam(name = "n") int n){
        return messageService.getMessageHistory(userId1, userId2, n);
    }

    @GetMapping("/queryRealtimeMessage")
    public List<MessageEntity> queryRealtimeMessage(@RequestParam(name = "userId1") Integer userId1,
                                                    @RequestParam(name = "userId2") Integer userId2,
                                                    @RequestParam(name = "loginTime") LocalTime loginTime){
        return messageService.queryRealtimeMessage(userId1, userId2, loginTime);
    }

    @PostMapping("/createRoom")
    public RoomEntity createRoom(@RequestBody RoomEntity room) {
        Optional<RoomEntity> existingRoom = roomService.getRoomByUserIds(room.getUserId1(), room.getUserId2());
        if (existingRoom.isPresent()) {
            return existingRoom.get();
        } else {
            return roomService.saveRoom(room);
        }
    }
}
