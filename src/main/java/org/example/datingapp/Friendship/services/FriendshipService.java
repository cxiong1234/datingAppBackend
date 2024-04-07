package org.example.datingapp.Friendship.services;

import org.example.datingapp.Friendship.entities.UserEntity;
import org.example.datingapp.Friendship.repositories.FriendshipRepository;
import org.example.datingapp.Friendship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendshipService(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    public List<UserEntity> getFriendsForUser(Integer userId) {
        // This assumes that findAllFriendsByUserId is correctly implemented in the repository
        return friendshipRepository.findAllFriendsByUserId(userId);
    }
    public boolean checkFriendship(Integer userId1, Integer userId2){
        return friendshipRepository.existsFriendshipBetweenUsers(userId1, userId2);
    }

}
