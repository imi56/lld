package com.practice.socialMedia;

import java.util.Set;

interface PostObserver {
  void onPostCreated(String authorId, Post post, Set<String> followers);
}
