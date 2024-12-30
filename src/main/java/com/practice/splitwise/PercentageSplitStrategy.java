package com.practice.splitwise;
import java.util.*;

class PercentageSplitStrategy implements SplitStrategy {
  public Map<User, Double> calculateSplit(double amount, List<User> participants, List<Double> values) {
      Map<User, Double> splitDetails = new HashMap<>();
      for (int i = 0; i < participants.size(); i++) {
          splitDetails.put(participants.get(i), (values.get(i) / 100) * amount);
      }
      return splitDetails;
  }
}
