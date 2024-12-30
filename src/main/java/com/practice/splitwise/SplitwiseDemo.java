package com.practice.splitwise;

import java.util.Arrays;
import java.util.List;

public class SplitwiseDemo {
  public static void main(String[] args) {
        SplitwiseService service = new SplitwiseService();

        service.addUser("1", "A", "a@example.com");
        service.addUser("2", "B", "b@example.com");
        service.addUser("3", "C", "c@example.com");

        // Split expense equally
        // service.splitExpense("e1", 300.0, "1", Arrays.asList("1", "2", "3"), SplitType.EQUAL, null);
        
        // Split expense exactly
        // service.splitExpense("e2", 300.0, "2", Arrays.asList("1", "2", "3"), SplitType.EXACT, Arrays.asList(100.0, 150.0, 50.0));
        
        // Split expense by percentage
        service.splitExpense("e3", 300.0, "3", Arrays.asList("1", "2", "3"), SplitType.PERCENTAGE, Arrays.asList(40.0, 40.0, 20.0));

        service.showExpenses();
        service.showExpensesForUser("1");

        List<Transaction> transactions = service.settleTransactions();
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
