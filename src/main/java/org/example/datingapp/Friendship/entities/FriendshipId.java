package org.example.datingapp.Friendship.entities;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class FriendshipId implements Serializable {
    private Integer user_id_1;
    private Integer user_id_2;

    // Default constructor
    public FriendshipId() {}

    // Constructor with fields
    public FriendshipId(Integer user_id_1, Integer user_id_2) {
        this.user_id_1 = user_id_1;
        this.user_id_2 = user_id_2;
    }

    // Getters and setters

    public Integer getUser_id_1() {
        return user_id_1;
    }

    public void setUser_id_1(Integer user_id_1) {
        this.user_id_1 = user_id_1;
    }

    public Integer getUser_id_2() {
        return user_id_2;
    }

    public void setUser_id_2(Integer user_id_2) {
        this.user_id_2 = user_id_2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendshipId that)) return false;
        return Objects.equals(user_id_1, that.user_id_1) && Objects.equals(user_id_2, that.user_id_2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id_1, user_id_2);
    }

    // hashCode() and equals() methods
}

