package com.practice.socialMedia;

import java.time.LocalDateTime;

public class Post {
    private final String postId;
    private final String author;
    private final String content;
    private final LocalDateTime timestamp;

    public Post(String postId, String author, String content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getPostId() {
        return postId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

