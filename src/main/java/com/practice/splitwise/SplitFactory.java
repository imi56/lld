package com.practice.splitwise;

class SplitFactory {
  public static SplitStrategy getSplitStrategy(SplitType splitType) {
      switch (splitType) {
          case EQUAL:
              return new EqualSplitStrategy();
          case EXACT:
              return new ExactSplitStrategy();
          case PERCENTAGE:
              return new PercentageSplitStrategy();
          default:
              throw new IllegalArgumentException("Invalid split type");
      }
  }
}
