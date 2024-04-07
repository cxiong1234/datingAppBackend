package org.example.datingapp.Friendship.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "friendships")
public class FriendshipEntity {
    @EmbeddedId
    private FriendshipId id;

    // No need for separate fields for user_id_1 and user_id_2, they are contained in the id

    // Constructors, getters, and setters

    public FriendshipEntity(FriendshipId id) {
        this.id = id;
    }

    public FriendshipEntity() {

    }

    public FriendshipId getId() {
        return id;
    }

    public void setId(FriendshipId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendshipEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

