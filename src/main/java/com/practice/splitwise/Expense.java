package com.practice.splitwise;
import java.util.*;

class Expense {
  String expenseId;
  double amount;
  User paidBy;
  List<User> participants;
  Map<User, Double> splitDetails;

  public Expense(String expenseId, double amount, User paidBy, List<User> participants, Map<User, Double> splitDetails) {
      this.expenseId = expenseId;
      this.amount = amount;
      this.paidBy = paidBy;
      this.participants = participants;
      this.splitDetails = splitDetails;
  }
}