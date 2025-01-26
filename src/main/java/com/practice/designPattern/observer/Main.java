package com.practice.designPattern.observer;

public class Main {
  public static void main(String[] args) {
    User user = new User("user@gmail.com");
    UserService userService = new UserService(user);
    EmailService emailService = new EmailService();
    userService.registerObserver(emailService);
    userService.createUser();
  }
}
