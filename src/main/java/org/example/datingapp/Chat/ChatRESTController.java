package org.example.datingapp.Chat;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ChatRESTController {

    @RequestMapping("/chat/messages")
    public String getMessages(@RequestParam(name = "chatId") Long chatId) {
        return "Messages retrieved for chatId: " + chatId;
    }
}