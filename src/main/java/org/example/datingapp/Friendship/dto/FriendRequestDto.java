package org.example.datingapp.Friendship.dto;

public class FriendRequestDto {
    private Integer userId;
    private Integer friendId;

    public FriendRequestDto(Integer userId, Integer friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    // Getters and setters
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return this.friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}