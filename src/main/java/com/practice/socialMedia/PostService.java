package com.practice.socialMedia;

import java.util.UUID;

public class PostService {
    private final FeedService feedService;
    private final UserService userService;

    public PostService(FeedService feedService, UserService userService) {
        this.feedService = feedService;
        this.userService = userService;
    }

    public void createPost(String userId, String content) {
        User author = userService.getUser(userId);
        if (author == null) {
            throw new IllegalArgumentException("User does not exist");
        }

        Post post = new Post(UUID.randomUUID().toString(), userId, content);

        // Notify followers and update feeds
        feedService.updateFeeds(userId, post, author.getFollowers());
    }
}

