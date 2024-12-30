package com.practice.splitwise;
import java.util.*;

class EqualSplitStrategy implements SplitStrategy {
  public Map<User, Double> calculateSplit(double amount, List<User> participants, List<Double> values) {
      Map<User, Double> splitDetails = new HashMap<>();
      double equalShare = amount / participants.size();
      for (User participant : participants) {
          splitDetails.put(participant, equalShare);
      }
      return splitDetails;
  }
}
