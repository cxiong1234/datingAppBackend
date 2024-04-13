package org.example.datingapp.Login;

import org.example.datingapp.Profile.ProfileResponse;

public class LoginResponse {
    public final ProfileResponse profileResponse;

    public final String token;

    public LoginResponse(ProfileResponse profileResponse, String token) {
        this.profileResponse = profileResponse;
        this.token = token;
    }
}
