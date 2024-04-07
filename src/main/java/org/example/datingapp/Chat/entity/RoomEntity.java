package org.example.datingapp.Chat.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalTime;


@Table("rooms")
public record RoomEntity(
        @Id Integer room_id,
        Integer user_id_1,
        Integer user_id_2,
        LocalTime create_time
) {
}

