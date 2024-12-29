package com.practice.socialMedia;

import java.util.List;
import java.util.NavigableSet;

public class DefaultFeedStrategyImpl implements FeedStrategy {
    @Override
    public List<Post> getFeed(NavigableSet<Post> posts, String cursor, int limit) {
        return posts.stream()
                .filter(post -> cursor == null || post.getPostId().compareTo(cursor) > 0)
                .limit(limit)
                .toList();
    }
}

