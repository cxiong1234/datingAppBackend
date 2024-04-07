package org.example.datingapp.Friendship.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendRequestId implements Serializable {
    private Integer senderUserId;
    private Integer receiverUserId;

    // Default constructor
    public FriendRequestId() {}

    // Constructor with fields
    public FriendRequestId(Integer senderUserId, Integer receiverUserId) {
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
    }

    // Getters and setters, equals(), and hashCode() methods

    public Integer getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Integer senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Integer getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Integer receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendRequestId that)) return false;
        return Objects.equals(senderUserId, that.senderUserId) && Objects.equals(receiverUserId, that.receiverUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderUserId, receiverUserId);
    }
}
