package org.example.datingapp.Profile;

public class ProfileResponse {
    public final String email;
    public final String nickName;
    public final String url;

    public ProfileResponse(String email, String nickName, String url) {
        this.email = email;
        this.nickName = nickName;
        this.url = url;
    }
}
