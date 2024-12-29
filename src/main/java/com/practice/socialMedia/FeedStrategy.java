package com.practice.socialMedia;

import java.util.List;
import java.util.NavigableSet;

public interface FeedStrategy {
    List<Post> getFeed(NavigableSet<Post> posts, String cursor, int limit);
}

