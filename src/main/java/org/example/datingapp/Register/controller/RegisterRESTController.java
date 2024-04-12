package org.example.datingapp.Register.controller;

import org.example.datingapp.Friendship.entities.UserEntity;
import org.example.datingapp.Friendship.repositories.UserRepository;
import org.example.datingapp.Profile.ProfileResponse;
import org.example.datingapp.Register.Dto.RegisterRequest;
import org.example.datingapp.Register.Dto.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterRESTController {

    private static final String PROFILE_URL = "https://png.pngtree.com/png-clipart/20211216/ourmid/pngtree-green-valentine-s-day-than-heart-confession-boy-cartoon-wechat-avatar-png-image_4068106.png";
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest request) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(request.email);
        newUser.setPassword(request.password);
        newUser.setNickname(request.nickname);
        newUser.setUrl(PROFILE_URL);
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            return new RegisterResponse(
                    null,
                    null,
                    "Email already exists");
        } else {
            UserEntity user = userRepository.save(newUser);
            return new RegisterResponse(
                    convertToResponse(user),
                    "asdfasdfadsfasdfasfsf",
                    null);
        }
    }

    private ProfileResponse convertToResponse(UserEntity user) {
        return new ProfileResponse(user.getEmail(), user.getNickname(), user.getUrl());
    }
}