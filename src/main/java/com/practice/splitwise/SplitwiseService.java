package com.practice.splitwise;
import java.util.*;

class SplitwiseService {
  private Map<String, User> users = new HashMap<>();
  private Map<User, Double> balanceSheet = new TreeMap<>(Comparator.comparing(u -> u.name));
  private List<Expense> expenses = new ArrayList<>();

  public void addUser(String userId, String name, String email) {
      User user = new User(userId, name, email);
      users.put(userId, user);
      balanceSheet.put(user, 0.0);
  }

  public void splitExpense(String expenseId, double amount, String paidByUserId, List<String> participantUserIds, SplitType splitType, List<Double> values) {
      User paidBy = users.get(paidByUserId);
      List<User> participants = new ArrayList<>();
      for (String participantUserId : participantUserIds) {
          participants.add(users.get(participantUserId));
      }

      SplitStrategy strategy = SplitFactory.getSplitStrategy(splitType);
      Map<User, Double> splitDetails = strategy.calculateSplit(amount, participants, values);

      Expense expense = new Expense(expenseId, amount, paidBy, participants, splitDetails);
      expenses.add(expense);
      addExpense(expense);
  }

  private void addExpense(Expense expense) {
      User paidBy = expense.paidBy;
      balanceSheet.put(paidBy, balanceSheet.getOrDefault(paidBy, 0.0) + expense.amount);

      for (Map.Entry<User, Double> entry : expense.splitDetails.entrySet()) {
          User user = entry.getKey();
          double share = entry.getValue();
          balanceSheet.put(user, balanceSheet.getOrDefault(user, 0.0) - share);
      }
  }

  public void showExpenses() {
      for (Expense expense : expenses) {
          System.out.println("Expense ID: " + expense.expenseId + ", Paid by: " + expense.paidBy.name + ", Amount: " + expense.amount);
      }
  }

  public void showExpensesForUser(String userId) {
      User user = users.get(userId);
      double balance = balanceSheet.getOrDefault(user, 0.0);
      System.out.println(user.name + " has a balance of: " + balance);
  }

  public List<Transaction> settleTransactions() {
      PriorityQueue<Map.Entry<User, Double>> creditors = new PriorityQueue<>((a, b) -> Double.compare(b.getValue(), a.getValue()));
      PriorityQueue<Map.Entry<User, Double>> debtors = new PriorityQueue<>((a, b) -> Double.compare(a.getValue(), b.getValue()));

      for (Map.Entry<User, Double> entry : balanceSheet.entrySet()) {
          if (entry.getValue() > 0) creditors.add(entry);
          else if (entry.getValue() < 0) debtors.add(entry);
      }

      List<Transaction> transactions = new ArrayList<>();

      while (!creditors.isEmpty() && !debtors.isEmpty()) {
          Map.Entry<User, Double> creditor = creditors.poll();
          Map.Entry<User, Double> debtor = debtors.poll();

          double settledAmount = Math.min(creditor.getValue(), -debtor.getValue());

          transactions.add(new Transaction(debtor.getKey(), creditor.getKey(), settledAmount));

          creditor.setValue(creditor.getValue() - settledAmount);
          debtor.setValue(debtor.getValue() + settledAmount);

          if (creditor.getValue() > 0) creditors.add(creditor);
          if (debtor.getValue() < 0) debtors.add(debtor);
      }

      return transactions;
  }
}

