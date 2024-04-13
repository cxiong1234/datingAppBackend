package org.example.datingapp.Friendship.dto;

import org.example.datingapp.Friendship.entities.FriendRequestEntity;
import org.example.datingapp.Friendship.entities.UserEntity;

public class FriendRequestProjectionDto {
    private FriendRequestEntity friendRequest;
    private UserEntity senderUser;
    private UserEntity receiverUser;

    public FriendRequestProjectionDto(FriendRequestEntity friendRequest, UserEntity senderUser, UserEntity receiverUser) {
        this.friendRequest = friendRequest;
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
    }

    public FriendRequestEntity getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequestEntity friendRequest) {
        this.friendRequest = friendRequest;
    }

    public UserEntity getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(UserEntity senderUser) {
        this.senderUser = senderUser;
    }

    public UserEntity getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(UserEntity receiverUser) {
        this.receiverUser = receiverUser;
    }
}