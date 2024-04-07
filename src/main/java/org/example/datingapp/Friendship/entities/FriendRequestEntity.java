package org.example.datingapp.Friendship.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import org.example.datingapp.Friendship.entities.RequestStatus;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "friend_requests")
public class FriendRequestEntity {



    @EmbeddedId
    private FriendRequestId id;


    @Column(name = "request_status", nullable = false)
    private String requestStatus;

    @Column(name = "send_time", nullable = false)
    private LocalDateTime sendTime;

    // Default constructor
    public FriendRequestEntity() {}

    // Constructor with fields

    // Getters and setters

    public FriendRequestId getId() {
        return id;
    }

    public void setId(FriendRequestId id) {
        this.id = id;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    //  equals(), and hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendRequestEntity that)) return false;
        return Objects.equals(id, that.id) && requestStatus == that.requestStatus && Objects.equals(sendTime, that.sendTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestStatus, sendTime);
    }
}
