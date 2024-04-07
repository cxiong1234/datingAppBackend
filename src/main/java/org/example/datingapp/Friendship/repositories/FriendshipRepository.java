package org.example.datingapp.Friendship.repositories;

import org.example.datingapp.Friendship.entities.FriendshipId;
import org.example.datingapp.Friendship.entities.FriendshipEntity;
import org.example.datingapp.Friendship.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<FriendshipEntity, FriendshipId> {

    // Query to find friendships where the user is either user_id_1 or user_id_2
    @Query("SELECT u FROM FriendshipEntity f JOIN UserEntity u ON f.id.user_id_2 = u.userId WHERE f.id.user_id_1 = ?1")
    List<UserEntity> findAllFriendsByUserId(Integer userId);

    // Query to check whether two friendships exists.
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FriendshipEntity f WHERE " +
            "(f.id.user_id_1 = ?1 AND f.id.user_id_2 = ?2) OR " +
            "(f.id.user_id_1 = ?2 AND f.id.user_id_2 = ?1)")
    boolean existsFriendshipBetweenUsers(Integer userId1, Integer userId2);
}


