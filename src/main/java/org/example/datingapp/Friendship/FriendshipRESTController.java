package org.example.datingapp.Friendship;

import org.example.datingapp.Friendship.entities.FriendRequestEntity;
import org.example.datingapp.Friendship.services.FriendRequestService;
import org.example.datingapp.Friendship.services.FriendshipService;
import org.example.datingapp.Friendship.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/addAFriend")
    public String AddAFriend(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "friendId") Integer friendId){
        boolean success = friendRequestService.addAFriend(userId, friendId);
        if (success) {
            return "Friend request sent successfully from user " + userId + " to user " + friendId;
        } else {
            return "Friend request already exists or failed to send.";
        }
    }

    @RequestMapping("/listAllFriendRequests")
    public List<FriendRequestEntity> checkFriendRequests(@RequestParam(name = "userId") Integer userId){

        return friendRequestService.getAllFriendRequestsToUser(userId);
    }

    @RequestMapping("/acceptOrDecline")
    public String AcceptOrDecline(@RequestParam(name = "decision") boolean decision, @RequestParam(name = "senderId") Integer senderId, @RequestParam(name = "receiverId") Integer receiverId){
        return friendRequestService.acceptOrDecline(decision, senderId, receiverId);
    }
}
