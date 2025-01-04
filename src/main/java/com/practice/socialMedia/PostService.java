package com.practice.socialMedia;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PostService implements PostSubject {
     private final List<PostObserver> observers = new ArrayList<>();

    private final FeedService feedService;
    private final UserService userService;

    public PostService(FeedService feedService, UserService userService) {
        this.feedService = feedService;
        this.userService = userService;
    }

    @Override
    public void registerObserver(PostObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PostObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String authorId, Post post, Set<String> followers) {
        for (PostObserver observer : observers) {
            observer.onPostCreated(authorId, post, followers);
        }
    }

    public void createPost(String userId, String content) {
        User author = userService.getUser(userId);
        if (author == null) {
            throw new IllegalArgumentException("User does not exist");
        }

        Post post = new Post(UUID.randomUUID().toString(), userId, content);

        // Notify followers and update feeds
        // feedService.updateFeeds(userId, post, author.getFollowers());
        notifyObservers(userId, post, author.getFollowers());
    }
}

