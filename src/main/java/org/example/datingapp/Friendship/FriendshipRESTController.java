package org.example.datingapp.Friendship;

import org.example.datingapp.Friendship.dto.AcceptOrDeclineDto;
import org.example.datingapp.Friendship.dto.FriendRequestProjectionDto;
import org.example.datingapp.Friendship.entities.FriendRequestEntity;
import org.example.datingapp.Friendship.services.FriendRequestService;
import org.example.datingapp.Friendship.services.FriendshipService;
import org.example.datingapp.Friendship.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.datingapp.Friendship.dto.FriendRequestDto;


import java.util.List;

@RestController
public class FriendshipRESTController {

    private final FriendshipService friendshipService;
    private final FriendRequestService friendRequestService;
    @Autowired
    public FriendshipRESTController(FriendshipService friendshipService, FriendRequestService friendRequestService) {
        this.friendshipService = friendshipService;
        this.friendRequestService = friendRequestService;
    }
    @RequestMapping("/checkFriendship")
    public boolean checkFriendshipStatus(@RequestParam(name = "userId") Integer userId,
                                        @RequestParam(name = "friendId") Integer friendId) {
        return friendshipService.checkFriendship(userId, friendId);
    }


    @RequestMapping("/listAllFriends")
    public List<UserEntity> listAllFriends(@RequestParam(name = "userId") Integer userId) {
        // Call the service to get the list of friends
        return friendshipService.getFriendsForUser(userId);
    }

    @PostMapping("/addAFriend")
    public String AddAFriend(@RequestBody FriendRequestDto friendRequestDto){
        boolean success = friendRequestService.addAFriend(friendRequestDto.getUserId(), friendRequestDto.getFriendId());
        if (success) {
            return "Friend request sent successfully from user " + friendRequestDto.getUserId() + " to user " + friendRequestDto.getFriendId();
        } else {
            return "Friend request already exists or failed to send.";
        }
    }
//    @RequestMapping("/addAFriend")
//    public String AddAFriend(@RequestParam(name="userId") Integer userId, @RequestParam(name="friendId") Integer friendId){
//        boolean success = friendRequestService.addAFriend(userId, friendId);
//        if (success) {
//            return "Friend request sent successfully from user " + userId + " to user " + friendId;
//        } else {
//            return "Friend request already exists or failed to send.";
//        }



    @RequestMapping("/listAllFriendRequests")
    public List<FriendRequestEntity> checkFriendRequests(@RequestParam(name = "userId") Integer userId){

        return friendRequestService.getAllFriendRequestsToUser(userId);
    }

    @RequestMapping("/listAllFriendRequestsNew")
    public ResponseEntity<List<FriendRequestProjectionDto>> listAllFriendRequests(@RequestParam Integer userId) {
        List<FriendRequestProjectionDto> projections = friendRequestService.getAllFriendRequestsAndRelatedUsers(userId);
        return ResponseEntity.ok(projections);
    }

    @PostMapping("/acceptOrDecline")
    public String AcceptOrDecline(@RequestBody AcceptOrDeclineDto acceptOrDeclineDto){
        return friendRequestService.acceptOrDecline(acceptOrDeclineDto.getDecision(), acceptOrDeclineDto.getSenderId(), acceptOrDeclineDto.getReceiverId());
    }
}
