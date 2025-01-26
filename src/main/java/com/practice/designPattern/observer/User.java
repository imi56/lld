package com.practice.designPattern.observer;

public class User {
  private String email;

  User(String email) {
    this.email = email;
  }

  String getEmail() {
    return this.email;
  }
}
