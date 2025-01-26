package com.practice.designPattern.observer;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserSubject {
  List<UserObserver> observers = new ArrayList<>();
  private User user;

  UserService(User user) {
    this.user = user;
  }

  // notify all user observers
  public boolean createUser() {
    System.out.println("User " + user.getEmail() + "saved into db");
    notifyObservers();
    return true;
  }

  @Override
  public void notifyObservers() {
    for(UserObserver observer : observers) {
      observer.onUserCreate(this.user);
    }
  }

  @Override
  public void registerObserver(UserObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver() {
    // TODO Auto-generated method stub
    
  }

}