package com.cryptoarb.Calculators;

import java.util.List;

public interface IProfitCalculator {
    double calculateNetProfit(List<Integer> cycle, double[][] negativeLogPrices);
}
