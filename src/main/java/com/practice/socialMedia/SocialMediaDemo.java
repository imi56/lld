package com.practice.socialMedia;

import java.util.List;

public class SocialMediaDemo {
  public static void main(String[] args) {
    FeedService feedService = new FeedService(new DefaultFeedStrategyImpl());
    UserService userService = new UserService();
    PostService postService = new PostService(feedService, userService);

    // Register users
    userService.registerUser("Alice");
    feedService.registerUserFeed("Alice");
    userService.registerUser("Bob");
    feedService.registerUserFeed("Bob");
    userService.registerUser("Charlie");
    feedService.registerUserFeed("Charlie");
    userService.registerUser("Diana");
    feedService.registerUserFeed("Diana");

    // Establish follow relationships
    userService.followUser("Bob", "Alice");
    userService.followUser("Charlie", "Alice");
    userService.followUser("Diana", "Alice");
    userService.followUser("Diana", "Charlie");

    // Alice creates posts
    postService.createPost("Alice", "Hello, this is my first post!");
    postService.createPost("Alice", "Loving this social media app!");
    postService.createPost("Alice", "The weather is great today.");
    postService.createPost("Alice", "Java coding is fun!");

    // Charlie creates posts
    postService.createPost("Charlie", "Excited to share my first post!");
    postService.createPost("Charlie", "Just watched an amazing movie.");
    postService.createPost("Charlie", "Learning about design patterns.");

    // Diana creates posts
    postService.createPost("Diana", "Hello, world!");
    postService.createPost("Diana", "Traveling to the mountains this weekend.");
    postService.createPost("Diana", "Python vs Java: Which one do you prefer?");

    // Retrieve Bob's feed
    System.out.println("Bob's Feed:");
    List<Post> bobFeed = feedService.getFeed("Bob", null, 10);
    for (Post post : bobFeed) {
        System.out.println(post.getAuthor() + ": " + post.getContent());
    }

    // Retrieve Diana's feed with pagination
    System.out.println("\nDiana's Feed (First Page):");
    List<Post> dianaFeedPage1 = feedService.getFeed("Diana", null, 5);
    for (Post post : dianaFeedPage1) {
        System.out.println(post.getAuthor() + ": " + post.getContent());
    }

    if (!dianaFeedPage1.isEmpty()) {
        String lastPostId = dianaFeedPage1.get(dianaFeedPage1.size() - 1).getPostId();
        System.out.println("\nDiana's Feed (Second Page):");
        List<Post> dianaFeedPage2 = feedService.getFeed("Diana", lastPostId, 5);
        for (Post post : dianaFeedPage2) {
            System.out.println(post.getAuthor() + ": " + post.getContent());
        }
    }

    // Retrieve Charlie's feed
    System.out.println("\nCharlie's Feed:");
    List<Post> charlieFeed = feedService.getFeed("Charlie", null, 10);
    for (Post post : charlieFeed) {
        System.out.println(post.getAuthor() + ": " + post.getContent());
    }

    // Shutdown FeedService Executor
    feedService.shutdown();
}
}

