package org.example.datingapp.Friendship.entities;

public enum RequestStatus {
    Pending, // Corresponds to 'Pending' in PostgreSQL
    Accepted, // Corresponds to 'Accepted'
    Rejected, // Corresponds to 'Rejected'
    Expired; // Corresponds to 'Expired'

    // Optionally override toString() if you need to match the exact string in the database

}
