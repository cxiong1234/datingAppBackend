package org.example.datingapp.Friendship.services;

import org.example.datingapp.Friendship.dto.FriendRequestProjectionDto;
import org.example.datingapp.Friendship.entities.*;
import org.example.datingapp.Friendship.repositories.FriendRequestRepository;
import org.example.datingapp.Friendship.repositories.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.datingapp.Friendship.entities.RequestStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final FriendshipRepository friendshipRepository;

    @Autowired
    public FriendRequestService(FriendRequestRepository friendRequestRepository, FriendshipRepository friendshipRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.friendshipRepository = friendshipRepository;
    }


    public boolean addAFriend(Integer senderUserId, Integer receiverUserId) {
        FriendRequestId friendRequestId = new FriendRequestId(senderUserId, receiverUserId);
        if (!friendRequestRepository.existsById(friendRequestId)) {
            FriendRequestEntity friendRequest = new FriendRequestEntity();
            friendRequest.setId(friendRequestId);
            friendRequest.setRequestStatus("Pending");
            friendRequest.setSendTime(LocalDateTime.now());
            friendRequestRepository.save(friendRequest);
            return true;
        }
        return false; // Friend request already exists
    }

    public List<FriendRequestEntity> getAllFriendRequestsToUser(Integer userId) {
        return friendRequestRepository.findByReceiverUserId(userId);
    }

    public List<FriendRequestProjectionDto> getAllFriendRequestsAndRelatedUsers(Integer userId) {
        List<Object[]> results = friendRequestRepository.findFriendRequestsAndUsers(userId);
        List<FriendRequestProjectionDto> projections = new ArrayList<>();
        for (Object[] result : results) {
            FriendRequestEntity friendRequest = (FriendRequestEntity) result[0];
            UserEntity sender = (UserEntity) result[1];
            UserEntity receiver = (UserEntity) result[2];
            projections.add(new FriendRequestProjectionDto(friendRequest, sender, receiver));
        }
        return projections;
    }

    // receiver accept or decline the friend request
    @Transactional
    public String acceptOrDecline(boolean decision, Integer senderId, Integer receiverId) {
        // Check if friend request exists
        if (friendRequestRepository.existsById(new FriendRequestId(senderId, receiverId))) {
            String status = decision ? "Accepted" : "Rejected";
            friendRequestRepository.updateRequestStatus(status, senderId, receiverId);

            if (decision) {
                // Accept: Create a new friendship
                FriendshipEntity friendship = new FriendshipEntity(new FriendshipId(senderId, receiverId));
                friendshipRepository.save(friendship);
                return "Friend request accepted.";
            } else {
                // Decline
                return "Friend request declined.";
            }
        } else {
            return "Friend request does not exist.";
        }
    }
}
