package com.practice.socialMedia;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final String userId;
    private final Set<String> followers;
    private final Set<String> following;

    public User(String userId) {
        this.userId = userId;
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    public String getUserId() {
        return userId;
    }

    public Set<String> getFollowers() {
        return followers;
    }

    public Set<String> getFollowing() {
        return following;
    }

    public void follow(String userId) {
        following.add(userId);
    }

    public void unfollow(String userId) {
        following.remove(userId);
    }
}

