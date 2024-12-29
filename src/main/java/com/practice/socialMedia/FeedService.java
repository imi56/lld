package com.practice.socialMedia;

import java.util.*;
import java.util.concurrent.*;

public class FeedService {
    // NavigableSet (TreeSet)	
    // self-balancing binary search tree (e.g., Red-Black Tree) under the hood.
    // provides an efficient way to store and access sorted data,
    // which is essential for maintaining a feed sorted by time.
    // Could be replaced by a PriorityQueue for sorting, 
    // but PriorityQueue lacks navigation methods and isn't as intuitive for feed pagination.
    // higher(), lower()
    private final Map<String, NavigableSet<Post>> userFeeds;
    private final FeedStrategy feedStrategy;
    private final ExecutorService executorService;

    public FeedService(FeedStrategy feedStrategy) {
      // ConcurrentHashMap is a thread-safe implementation of Map
      //it allows multiple threads to access it concurrently 
      // without requiring explicit synchronization.
        this.userFeeds = new ConcurrentHashMap<>();
        this.feedStrategy = feedStrategy;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public List<Post> getFeed(String userId, String cursor, int limit) {
        NavigableSet<Post> feed = userFeeds.getOrDefault(userId, new TreeSet<>(Comparator.comparing(Post::getTimestamp).reversed()));
        return feedStrategy.getFeed(feed, cursor, limit);
    }

    public void registerUserFeed(String userId) {
        userFeeds.putIfAbsent(userId, new TreeSet<>(Comparator.comparing(Post::getTimestamp).reversed()));
    }

    public void updateFeeds(String authorId, Post post, Set<String> followers) {
        executorService.submit(() -> {
            for (String followerId : followers) {
                userFeeds.computeIfAbsent(
                  followerId, k -> new TreeSet<>(Comparator.comparing(Post::getTimestamp).reversed())
                ).add(post);
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
