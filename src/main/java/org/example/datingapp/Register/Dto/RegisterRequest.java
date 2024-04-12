package org.example.datingapp.Register.Dto;

public class RegisterRequest {
    private String email;
    private String password;
    private String nickname;

    // Constructor, Getters, and Setters
    public RegisterRequest() {}

    public RegisterRequest(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    // Getter and setter methods
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
