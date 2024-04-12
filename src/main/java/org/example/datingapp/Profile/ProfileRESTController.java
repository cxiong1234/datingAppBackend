package org.example.datingapp.Profile;

import org.example.datingapp.Friendship.entities.UserEntity;
import org.example.datingapp.Friendship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ProfileRESTController {
    @Autowired
    private UserRepository repository;

    @RequestMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(
            @RequestParam(name = "user_id") Integer userId
    ) {
        return new ResponseEntity(
                convertToResponse(repository.getReferenceById(userId)),
                HttpStatus.OK);
    }

    private ProfileResponse convertToResponse(UserEntity user) {
        return new ProfileResponse(user.getEmail(), user.getNickname(), user.getUrl());
    }
}

