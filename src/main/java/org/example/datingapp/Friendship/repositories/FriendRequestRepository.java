package org.example.datingapp.Friendship.repositories;

import org.example.datingapp.Friendship.entities.FriendRequestEntity;
import org.example.datingapp.Friendship.entities.FriendRequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, FriendRequestId> {
    // Additional methods if necessary
    @Query("SELECT fr FROM FriendRequestEntity fr WHERE fr.id.receiverUserId = :receiverUserId")
    List<FriendRequestEntity> findByReceiverUserId(Integer receiverUserId);

    @Query("SELECT fr, sender, receiver FROM FriendRequestEntity fr " +
            "JOIN UserEntity sender ON fr.id.senderUserId = sender.userId " +
            "JOIN UserEntity receiver ON fr.id.receiverUserId = receiver.userId " +
            "WHERE fr.id.receiverUserId = :userId OR fr.id.senderUserId = :userId")
    List<Object[]> findFriendRequestsAndUsers(@Param("userId") Integer userId);


    // Change the Request status
    @Modifying
    @Transactional
    @Query("UPDATE FriendRequestEntity fr SET fr.requestStatus = :status WHERE fr.id.senderUserId = :senderId AND fr.id.receiverUserId = :receiverId")
    int updateRequestStatus(@Param("status") String status, @Param("senderId") Integer senderId, @Param("receiverId") Integer receiverId);


}
