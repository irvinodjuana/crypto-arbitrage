package com.cryptoarb.Configuration;

public class ConfigurationProvider implements IConfigurationProvider {
    private String binanceApiUri;
    private String binanceStreamUri;
    private double binanceFee;
    private long cycleDelayMs;
    private long processTimeoutMs;

    public ConfigurationProvider() {
        binanceApiUri = "https://api.binance.com/api/v3";
        binanceStreamUri = "wss://stream.binance.com:9443";
        binanceFee = 0.00075;
        cycleDelayMs = 1000;
        processTimeoutMs = 60000;
    }

    public String getBinanceApiUri() {
        return binanceApiUri;
    }

    public String getBinanceStreamUri() {
        return binanceStreamUri;
    }

    public double getBinanceFee() {
        return binanceFee;
    }

    public long getCycleDelayMs() {
        return cycleDelayMs;
    }

    public long getProcessTimeoutMs() {
        return processTimeoutMs;
    }
}
