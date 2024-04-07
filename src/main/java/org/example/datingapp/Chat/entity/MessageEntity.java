package org.example.datingapp.Chat.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalTime;


@Table("messages")
public record MessageEntity(
        @Id Integer message_id,
        Integer sender_user_id,
        Integer receiver_user_id,
        String text,
        Boolean is_read,
        LocalTime send_time
) {
}

