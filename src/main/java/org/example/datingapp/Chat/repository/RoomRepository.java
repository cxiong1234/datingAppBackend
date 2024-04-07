package org.example.datingapp.Chat.repository;

import org.example.datingapp.Chat.entity.RoomEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;


public interface RoomRepository extends ListCrudRepository<RoomEntity, Integer> {


    RoomEntity getByRoomId(Integer room_id);

//    @Modifying
//    @Query("UPDATE carts SET total_price = :totalPrice WHERE id = :cartId")
//    void updateTotalPrice(Long cartId, Double totalPrice);

    void isRoomExist(Integer user_id_1, Integer user_id_2);
}

