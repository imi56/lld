package com.practice.cab_booking;

import java.time.LocalTime;

public class DemandHourCalculator {
  public static DemandHour getCurrentDemandHour() {
        LocalTime now = LocalTime.now();

        if (now.isAfter(LocalTime.of(6, 0)) && now.isBefore(LocalTime.of(10, 0))) {
            return DemandHour.PEAK;
        } else if (now.isAfter(LocalTime.of(22, 0)) || now.isBefore(LocalTime.of(6, 0))) {
            return DemandHour.NIGHT;
        } else {
            return DemandHour.BASE;
        }
    }
}
