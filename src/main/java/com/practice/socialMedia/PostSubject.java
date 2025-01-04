package com.practice.socialMedia;

import java.util.Set;
interface PostSubject {
  void registerObserver(PostObserver observer);
  void removeObserver(PostObserver observer);
  void notifyObservers(String authorId, Post post, Set<String> followers);
}
