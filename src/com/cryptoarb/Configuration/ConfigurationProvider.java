package com.cryptoarb.Configuration;

public class ConfigurationProvider implements IConfigurationProvider {
    private String BinanceApiUri;
    private String BinanceStreamUri;

    public ConfigurationProvider() {
        BinanceApiUri = "https://api.binance.com/api/v3";
        BinanceStreamUri = "wss://stream.binance.com:9443";
    }

    public String getBinanceApiUri() {
        return BinanceApiUri;
    }

    public String getBinanceStreamUri() {
        return BinanceStreamUri;
    }
}
