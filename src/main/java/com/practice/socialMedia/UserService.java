package com.practice.socialMedia;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private final Map<String, User> users;

    public UserService() {
        this.users = new ConcurrentHashMap<>();
    }

    public User registerUser(String userId) {
        if (users.containsKey(userId)) {
            throw new IllegalArgumentException("User already exists");
        }
        User user = new User(userId);
        users.put(userId, user);
        return user;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void followUser(String followerId, String followeeId) {
        User follower = users.get(followerId);
        User followee = users.get(followeeId);

        if (follower == null || followee == null) {
            throw new IllegalArgumentException("One or both users do not exist");
        }

        follower.follow(followeeId);
        followee.getFollowers().add(followerId);
    }

    public void unfollowUser(String followerId, String followeeId) {
        User follower = users.get(followerId);
        User followee = users.get(followeeId);

        if (follower == null || followee == null) {
            throw new IllegalArgumentException("One or both users do not exist");
        }

        follower.unfollow(followeeId);
        followee.getFollowers().remove(followerId);
    }
}

