package org.example.datingapp.Register.Dto;

public class UserResponse {
    private Long userId;
    private String email;
    private String nickname;
    private String message;

    public UserResponse(Long userId, String email, String nickname, String message) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.message = message;
    }

    // Getter and setter methods
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
