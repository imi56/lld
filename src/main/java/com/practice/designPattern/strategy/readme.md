### Implement strategy design pattern. Assume the cab fare dependts on cab type, pricing strategy.

CapType -> MINI, SEDAN
DemandHour -> SURGE, NIGHT, BASIC
interface PricingStrategy
  - calculateFare()

PricingStrategyFactory -> decides which pricing factory to use since there are many 