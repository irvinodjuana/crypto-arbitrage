package com.cryptoarb.Configuration;

public interface IConfigurationProvider {
    String getBinanceApiUri();
    String getBinanceStreamUri();
    double getBinanceFee();
    long getCycleDelayMs();
    long getProcessTimeoutMs();
}
