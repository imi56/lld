package com.practice.designPattern.observer;

public class EmailService implements UserObserver {

  @Override
  public void onUserCreate(User user) {
    System.out.println("Email sent to user: " + user.getEmail());
  }
}
