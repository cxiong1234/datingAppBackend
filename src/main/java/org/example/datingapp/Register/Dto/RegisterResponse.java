package org.example.datingapp.Register.Dto;

import org.example.datingapp.Profile.ProfileResponse;

public class RegisterResponse {
    public final ProfileResponse profileResponse;

    public final String token;

    public final String message;

    public RegisterResponse(ProfileResponse profileResponse, String token, String message) {
        this.profileResponse = profileResponse;
        this.token = token;
        this.message = message;
    }
}
