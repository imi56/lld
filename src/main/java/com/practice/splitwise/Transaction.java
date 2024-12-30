package com.practice.splitwise;

public class Transaction {
   User fromUser;
    User toUser;
    double amount;

    public Transaction(User fromUser, User toUser, double amount) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return fromUser.name + " pays " + amount + " to " + toUser.name;
    }
}
