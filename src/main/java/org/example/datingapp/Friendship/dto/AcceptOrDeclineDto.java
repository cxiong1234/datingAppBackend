package org.example.datingapp.Friendship.dto;

public class AcceptOrDeclineDto {
    private boolean decision;
    private Integer senderId;
    private Integer receiverId;

    public AcceptOrDeclineDto(boolean decision, Integer senderId, Integer receiverId) {
        this.decision = decision;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public boolean getDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}
