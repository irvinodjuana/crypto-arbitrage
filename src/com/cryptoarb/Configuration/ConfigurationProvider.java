package com.cryptoarb.Configuration;

public class ConfigurationProvider implements IConfigurationProvider {
    private String binanceApiUri;
    private String binanceStreamUri;

    public ConfigurationProvider() {
        binanceApiUri = "https://api.binance.com/api/v3";
        binanceStreamUri = "wss://stream.binance.com:9443";
    }

    public String getBinanceApiUri() {
        return binanceApiUri;
    }

    public String getBinanceStreamUri() {
        return binanceStreamUri;
    }
}
