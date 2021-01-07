package com.cryptoarb.Calculators;

import com.cryptoarb.Configuration.IConfigurationProvider;

import java.util.List;

public class ProfitCalculator implements IProfitCalculator {
    private double tradingFee;

    public ProfitCalculator(IConfigurationProvider configurationProvider) {
        tradingFee = configurationProvider.getBinanceFee();
    }

    public double calculateNetProfit(List<Integer> cycle, double[][] negativeLogPrices) {
        double negativeLogWeight = 0;

        for (int i = 0; i < cycle.size() - 1; i++) {
            int src = cycle.get(i);
            int dst = cycle.get(i+1);
            negativeLogWeight += negativeLogPrices[src][dst];
        }

        double grossProfit = Math.exp(-1 * negativeLogWeight);
        double postFeesFactor = Math.pow(1.0 - tradingFee, cycle.size() - 1);

        return grossProfit * postFeesFactor;
    }
}
