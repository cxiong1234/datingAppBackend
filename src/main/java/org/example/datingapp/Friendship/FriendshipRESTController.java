package org.example.datingapp.Friendship;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FriendshipRESTController {

    @RequestMapping("/friendship")
    public String checkFriendshipStatus(@RequestParam(name = "userId") Long userId,
                                        @RequestParam(name = "friendId") Long friendId) {
        return "Friendship status checked for users: " + userId + " and " + friendId;
    }
}
