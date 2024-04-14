package org.example.datingapp.Friendship;

import org.example.datingapp.Friendship.dto.AcceptOrDeclineDto;
import org.example.datingapp.Friendship.dto.FriendRequestProjectionDto;
import org.example.datingapp.Friendship.entities.FriendRequestEntity;
import org.example.datingapp.Friendship.repositories.UserRepository;
import org.example.datingapp.Friendship.services.FriendRequestService;
import org.example.datingapp.Friendship.services.FriendshipService;
import org.example.datingapp.Friendship.entities.UserEntity;
import org.example.datingapp.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.datingapp.Friendship.dto.FriendRequestDto;


import java.util.List;
import java.util.Optional;

//@RestController
//public class FriendshipRESTController {
//
//    private final FriendshipService friendshipService;
//    private final FriendRequestService friendRequestService;
//    @Autowired
//    public FriendshipRESTController(FriendshipService friendshipService, FriendRequestService friendRequestService) {
//        this.friendshipService = friendshipService;
//        this.friendRequestService = friendRequestService;
//    }
//    @RequestMapping("/checkFriendship")
//    public boolean checkFriendshipStatus(@RequestParam(name = "userId") Integer userId,
//                                        @RequestParam(name = "friendId") Integer friendId) {
//        return friendshipService.checkFriendship(userId, friendId);
//    }
//


// ACCESS THE authenticated USER

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.example.datingapp.Friendship.repositories.UserRepository;
@RestController
@RequestMapping("/api/friends")
public class FriendshipRESTController {

    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private FriendRequestService friendRequestService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/listAllFriends")
    public ResponseEntity<?> listAllFriends() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        String email = authentication.getName(); // Assuming the username in the auth token is the user's email.

        Optional<UserEntity> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        else {
            UserEntity user1 = userRepository.findByEmail(email).get();
            List<UserEntity> friends = friendshipService.getFriendsForUser(user1.getUserId());
            return ResponseEntity.ok(friends);
        }
    }

    @GetMapping("/listAllFriendRequests")
    public ResponseEntity<?> listAllFriendRequests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        String email = authentication.getName(); // Assuming the username in the auth token is the user's email.

        Optional<UserEntity> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        else {
            UserEntity user1 = userRepository.findByEmail(email).get();
            List<FriendRequestProjectionDto> projections = friendRequestService.getAllFriendRequestsAndRelatedUsers(user1.getUserId());
            return ResponseEntity.ok(projections);
        }
    }

    @GetMapping("/addAFriend")
    public ResponseEntity<?> addAFriend(@RequestParam(name = "friendEmail") String friendEmail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        String email = authentication.getName(); // Assuming the username in the auth token is the user's email.

        Optional<UserEntity> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        else {
            UserEntity user1 = userRepository.findByEmail(email).get();
            Optional<UserEntity> friend = userRepository.findByEmail(friendEmail);

            if (!friend.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Friend not found");
            }
            else {
                UserEntity friend1 = userRepository.findByEmail(friendEmail).get();
                boolean success = friendRequestService.addAFriend(user1.getUserId(), friend1.getUserId());
                if (success) {
                    return ResponseEntity.ok("Friend request sent successfully from user " + user1.getUserId() + " to user " + friend1.getUserId());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Friend request already exists or failed to send.");
                }
            }
        }
    }

    @PostMapping("/acceptOrDecline")
    public ResponseEntity<?> acceptOrDecline(@RequestParam(name = "senderEmail") String senderEmail, @RequestParam(name = "decision") boolean decision) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        String email = authentication.getName(); // Assuming the username in the auth token is the user's email.

        Optional<UserEntity> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else {
            UserEntity user1 = userRepository.findByEmail(email).get();
            Optional<UserEntity> sender = userRepository.findByEmail(senderEmail);

            if (!sender.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sender not found");
            } else {
                UserEntity sender1 = userRepository.findByEmail(senderEmail).get();
                String result = friendRequestService.acceptOrDecline(decision, sender1.getUserId(), user1.getUserId());
                return ResponseEntity.ok(result);
            }
        }


    }

}
