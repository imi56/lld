package com.practice.designPattern.observer;

public interface UserSubject {
  void registerObserver(UserObserver observer);
  void removeObserver();
  void notifyObservers();
}
